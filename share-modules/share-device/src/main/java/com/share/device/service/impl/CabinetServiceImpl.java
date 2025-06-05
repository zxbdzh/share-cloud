package com.share.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.exception.ServiceException;
import com.share.common.security.utils.SecurityUtils;
import com.share.device.domain.Cabinet;
import com.share.device.domain.CabinetSlot;
import com.share.device.domain.CabinetType;
import com.share.device.mapper.CabinetMapper;
import com.share.device.mapper.CabinetSlotMapper;
import com.share.device.mapper.CabinetTypeMapper;
import com.share.device.service.ICabinetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CabinetServiceImpl extends ServiceImpl<CabinetMapper, Cabinet> implements ICabinetService {

    @Resource
    CabinetMapper cabinetMapper;

    @Resource
    CabinetTypeMapper cabinetTypeMapper;

    @Resource
    CabinetSlotMapper cabinetSlotMapper;

    @Override
    public List<CabinetType> selectCabinetList(Cabinet cabinet) {
        return cabinetMapper.selectCabinetList(cabinet);
    }

    /**
     * 保存充电柜机信息
     * @param cabinet 充电柜机信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int saveCabinet(Cabinet cabinet) {
        long count = this.count(new LambdaQueryWrapper<Cabinet>().eq(Cabinet::getCabinetNo, cabinet.getCabinetNo()));
        if (count > 0) throw new ServiceException("该柜机编号已存在");
        // 根据柜机类型id查询柜机类型
        Cabinet cabinetType = cabinetMapper.selectById(cabinet.getCabinetTypeId());

        // 设置总插槽数量和可用插槽数量
        cabinet.setTotalSlots(cabinetType.getTotalSlots());
        cabinet.setFreeSlots(cabinet.getTotalSlots());
        cabinet.setUsedSlots(0);
        cabinet.setAvailableNum(0);
        cabinet.setCreateTime(new Date());
        cabinet.setCreateBy(SecurityUtils.getUsername());
        cabinet.setDelFlag("0");
        this.save(cabinet);

        getSize(cabinet, cabinet.getTotalSlots());
        return 1;
    }

    private void getSize(Cabinet cabinet, Integer totalSlots) {
        int size = totalSlots;
        for (int i = 0; i < size; i++) {
            CabinetSlot cabinetSlot = new CabinetSlot();
            cabinetSlot.setCabinetId(cabinet.getId());
            cabinetSlot.setSlotNo(i+1+"");
            cabinetSlot.setCreateTime(new Date());
            cabinet.setCreateBy(SecurityUtils.getUsername());
            cabinetSlotMapper.insert(cabinetSlot);
        }
    }

    /**
     * 修改充电柜机信息
     * @param cabinet 充电柜机信息
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateCabinet(Cabinet cabinet) {
        // 获取旧的数据
        Cabinet oldCabinet = this.getById(cabinet.getId());
        if (null != oldCabinet && !"0".equals(oldCabinet.getStatus())) {
            throw new ServiceException("该柜机已投放, 无法修改");
        }
        // 判断柜机编号是否改变
        if (oldCabinet != null && !oldCabinet.getCabinetNo().equals(cabinet.getCabinetNo())) {
            long count = this.count(new LambdaQueryWrapper<Cabinet>().eq(Cabinet::getCabinetNo, cabinet.getCabinetNo()));
            if (count > 0) throw new ServiceException("该柜机编号已存在");
        }
        // 判断是否修改了柜机类型
        if (oldCabinet != null && oldCabinet.getCabinetTypeId().longValue() != cabinet.getCabinetTypeId()) {
            // 根据柜机类型id查询柜机类型
            CabinetType cabinetType = cabinetTypeMapper.selectById(cabinet.getCabinetTypeId());

            // 设置总插槽数量和可用插槽数量
            cabinet.setTotalSlots(cabinetType.getTotalSlots());
            cabinet.setTotalSlots(cabinetType.getTotalSlots());
            cabinet.setFreeSlots(cabinetType.getTotalSlots());
            cabinet.setUsedSlots(0);
            cabinet.setAvailableNum(0);
            cabinet.setUpdateTime(new Date());
            cabinet.setUpdateBy(SecurityUtils.getUsername());
            this.updateById(cabinet);

            // 删除所有插槽
            cabinetSlotMapper.delete(new LambdaQueryWrapper<CabinetSlot>().eq(CabinetSlot::getCabinetId, cabinet.getId()));
            getSize(cabinet, cabinetType.getTotalSlots());
        }
        return 1;
    }

    /**
     * 删除充电柜机信息
     * @param idList 充电柜机id集合
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeCabinet(List<Long> idList) {
        this.removeBatchByIds(idList);
        cabinetSlotMapper.delete(new LambdaQueryWrapper<CabinetSlot>().in(CabinetSlot::getCabinetId, idList));
        return 1;
    }

    /**
     * 查询未使用的充电柜机
     * @param keyword 关键词
     * @return 结果
     */
    public List<Cabinet> searchNoUseList(String keyword) {
        return cabinetMapper.selectList(new LambdaQueryWrapper<Cabinet>()
                .like(Cabinet::getCabinetNo, keyword)
                .eq(Cabinet::getStatus, "0")
        );
    }
}
