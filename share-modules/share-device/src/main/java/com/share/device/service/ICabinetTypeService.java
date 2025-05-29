package com.share.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.device.domain.CabinetType;

import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/5/28
 * Project: share-parent
 */
public interface ICabinetTypeService extends IService<CabinetType> {
    /**
     * 查询柜机类型列表
     *
     * @param cabinetType 柜机类型信息
     * @return 柜机类型集合
     */
    public List<CabinetType> selectCabinetTypeList(CabinetType cabinetType);
}
