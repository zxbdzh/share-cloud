package com.share.device.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.common.core.utils.StringUtils;
import com.share.device.domain.Region;
import com.share.device.mapper.RegionMapper;
import com.share.device.service.IRegionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/9
 * Project: share-parent
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {

    @Resource
    private RegionMapper regionMapper;

    public List<Region> treeSelect(String parentCode) {
        List<Region> regionList = regionMapper.selectList(new LambdaQueryWrapper<Region>().eq(Region::getParentCode, parentCode));
        if (!CollectionUtils.isEmpty(regionList)) {
            regionList.forEach(item -> {
                long count = regionMapper.selectCount(new LambdaQueryWrapper<Region>().eq(Region::getParentCode, item.getParentCode()));
                item.setHasChildren(count > 0);
            });
        }
        return regionList;
    }

    /**
     * 根据code获取名称
     * @param code
     * @return
     */
    public String getNameByCode(String code) {
        if (StringUtils.isEmpty(code)) return "";
        Region region = regionMapper.selectOne(new LambdaQueryWrapper<Region>().eq(Region::getCode, code).select(Region::getName));
        if (region != null) return region.getName();
        return "";
    }

}
