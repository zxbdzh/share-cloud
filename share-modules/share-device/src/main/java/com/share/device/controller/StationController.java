package com.share.device.controller;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.device.domain.Station;
import com.share.device.service.ICabinetService;
import com.share.device.service.IStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "站点接口管理")
@RestController
@RequestMapping("/station")
public class StationController extends BaseController {

    @Resource
    private IStationService stationService;

    @Resource
    private ICabinetService cabinetService;


    /**
     * 查询站点列表
     * @param station 查询参数
     * @return 站点列表
     */
    @Operation(summary = "查询站点列表")
    @GetMapping("/list")
    public TableDataInfo list(Station station) {
        startPage();
        List<Station> list = stationService.selectStationList(station);
        return getDataTable(list);
    }

    /**
     * 新增站点
     * @param station 站点信息
     * @return 结果
     */
    @Operation(summary = "新增站点")
    @PostMapping
    public AjaxResult add(@RequestBody Station station) {
        return toAjax(stationService.saveStation(station));
    }

    @Operation(summary = "修改站点")
    @PutMapping
    public AjaxResult edit(@RequestBody Station station) {
        return toAjax(stationService.updateStation(station));
    }

    @Operation(summary = "获取站点详细信息")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return success(stationService.getById(id));
    }

    @Operation(summary = "删除站点")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(stationService.removeBatchByIds(Arrays.asList(ids)));
    }

    @Operation(summary = "站点设置关联数据")
    @PostMapping("/setData")
    public AjaxResult setData(@RequestBody Station station) {
        return toAjax(stationService.setData(station));
    }

    @Operation(summary = "获取充电宝柜机全部详细信息")
    @GetMapping(value = "/getAllInfo/{id}")
    public AjaxResult getAllInfo(@PathVariable("id") Long id)
    {
        return success(cabinetService.getAllInfo(id));
    }

}
