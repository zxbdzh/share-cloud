package com.share.device.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.device.domain.CabinetType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/5/28
 * Project: share-parent
 */
public interface CabinetTypeMapper extends BaseMapper<CabinetType> {
    /**
     * 查询柜机类型列表
     * @param cabinetType
     * @return
     */
    List<CabinetType> selectCabinetTypeList(CabinetType cabinetType);
}
