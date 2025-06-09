package com.share.device.controller;

import com.share.common.core.web.controller.BaseController;
import com.share.common.core.web.domain.AjaxResult;
import com.share.common.core.web.page.TableDataInfo;
import com.share.device.domain.Station;
import com.share.device.service.IStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "站点接口管理")
@RestController
@RequestMapping("/station")
public class StationController extends BaseController {

    @Resource
    private IStationService stationService;

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

}
