package com.share.device.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.share.device.domain.CabinetSlot;

public interface CabinetSlotMapper {
    /**
     * 新增充电宝插槽充电宝
     * @param cabinetSlot
     */
    void insert(CabinetSlot cabinetSlot);

    void delete(LambdaQueryWrapper<CabinetSlot> eq);
}
