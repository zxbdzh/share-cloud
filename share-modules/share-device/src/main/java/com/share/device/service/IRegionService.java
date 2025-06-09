package com.share.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.device.domain.Region;

import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/9
 * Project: share-parent
 */
public interface IRegionService extends IService<Region> {
    /**
     * 获取下级数据列表
     * @param parentCode
     * @return
     */
    List<Region> treeSelect(String parentCode);

    /**
     * 根据code获取名称
     * @param code
     * @return
     */
    String getNameByCode(String code);

}
