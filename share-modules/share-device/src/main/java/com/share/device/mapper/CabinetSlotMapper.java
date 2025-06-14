package com.share.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.device.domain.CabinetSlot;

public interface CabinetSlotMapper extends BaseMapper<CabinetSlot> {
    /**
     * 新增充电宝插槽充电宝
     *
     * @param cabinetSlot
     * @return
     */
    int insert(CabinetSlot cabinetSlot);

    void delete(LambdaQueryWrapper<CabinetSlot> eq);
}
