package com.share.device.controller;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.common.security.utils.SecurityUtils;
import com.share.device.domain.PowerBank;
import com.share.device.service.IPowerBankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/4
 * Project: share-parent
 */
@Tag(name = "充电宝接口管理")
@RestController
@RequestMapping("/powerBank")
public class PowerBankController extends BaseController {

    @Resource
    private IPowerBankService powerBankService;

    /**
     * 查询充电宝列表
     * @param powerBank 充电宝
     * @return 充电宝列表
     */
    @Operation(summary = "查询充电宝列表")
    @GetMapping("/list")
    public TableDataInfo list(PowerBank powerBank) {
        startPage();
        List<PowerBank> list = powerBankService.selectPowerBankList(powerBank);
        return getDataTable(list);
    }

    /**
     * 获取充电宝详细信息
     * @param id 充电宝ID
     * @return 充电宝详细信息
     */
    @Operation(summary = "获取充电宝详细信息")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id){
        return success(powerBankService.getById(id));
    }

    /**
     * 新增充电宝
     * @param powerBank 充电宝
     * @return 影响行数
     */
    @Operation(summary = "新增充电宝")
    @PostMapping
    public AjaxResult add(@RequestBody PowerBank powerBank) {
        powerBank.setCreateBy(SecurityUtils.getUsername());
        powerBank.setCreateTime(new Date());
        return toAjax(powerBankService.savePowerBank(powerBank));
    }

    /**
     * 删除充电宝
     * @param ids 充电宝ID集合
     * @return 影响行数
     */
    @Operation(summary = "删除充电宝")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
       return toAjax(powerBankService.removeBatchByIds(Arrays.asList(ids)));
    }

}
