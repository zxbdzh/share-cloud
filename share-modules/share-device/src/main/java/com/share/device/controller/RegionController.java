package com.share.device.controller;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.device.service.IRegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/9
 * Project: share-parent
 */
@Tag(name = "地区信息接口管理")
@RestController
@RequestMapping("/region")
public class RegionController extends BaseController {
    @Resource
    private IRegionService regionService;

    @Operation(summary = "根据上级code获取下级数据列表")
    @GetMapping("/treeSelect/{parentCode}")
    public AjaxResult treeSelect(@PathVariable String parentCode) {
        return success(regionService.treeSelect(parentCode));
    }

}
