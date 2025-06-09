package com.share.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.exception.ServiceException;
import com.share.device.domain.PowerBank;
import com.share.device.mapper.PowerBankMapper;
import com.share.device.service.IPowerBankService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/4
 * Project: share-parent
 */
@Service
public class PowerBankServiceImpl extends ServiceImpl<PowerBankMapper, PowerBank> implements IPowerBankService {

    @Resource
    private PowerBankMapper powerBankMapper;

    /**
     * 查询充电宝列表
     * @param powerBank 充电宝
     * @return 充电宝列表
     */
    public List<PowerBank> selectPowerBankList(PowerBank powerBank) {
        return powerBankMapper.selectPowerBankList(powerBank);
    }

    /**
     * 根据充电宝编号查询充电宝
     * @param powerBankNo 充电宝编号
     * @return 充电宝
     */
    public PowerBank getByPowerBankNo(String powerBankNo) {
        return powerBankMapper.selectOne(new LambdaQueryWrapper<PowerBank>().eq(PowerBank::getPowerBankNo, powerBankNo));
    }

    /**
     * 保存充电宝
     * @param powerBank 充电宝
     * @return 结果
     */
    public int savePowerBank(PowerBank powerBank) {
        long count = this.count(new LambdaQueryWrapper<PowerBank>().eq(PowerBank::getPowerBankNo, powerBank.getPowerBankNo()));
        if (count > 0) throw new ServiceException("该充电宝编号已存在");
        powerBankMapper.insert(powerBank);
        return 1;
    }

    @Override
    public int updatePowerBank(PowerBank powerBank) {
        // 获取旧数据
        PowerBank oldPowerBank = this.getById(powerBank.getId());
        if (oldPowerBank != null && "0".equals(oldPowerBank.getStatus())) throw new ServiceException("该充电宝已投放, 无法修改");
        // 判断柜机编号是否更改
        if (!oldPowerBank.getPowerBankNo().equals(powerBank.getPowerBankNo())) {
            long count = this.count(new LambdaQueryWrapper<PowerBank>().eq(PowerBank::getPowerBankNo, powerBank.getPowerBankNo()));
            if (count > 0) throw new ServiceException("该充电宝编号已存在");
        }
        powerBankMapper.updateById(powerBank);
        return 1;
    }
}
