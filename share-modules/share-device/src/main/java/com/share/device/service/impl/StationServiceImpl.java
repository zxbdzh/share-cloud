package com.share.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.share.device.domain.Cabinet;
import com.share.device.domain.Station;
import com.share.device.mapper.StationMapper;
import com.share.device.service.ICabinetService;
import com.share.device.service.IRegionService;
import com.share.device.service.IStationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements IStationService {

    @Resource
    private StationMapper stationMapper;

    @Resource
    private ICabinetService cabinetService;

    @Resource
    private IRegionService regionService;

    /**
     * 查询站点列表
     *
     * @param station 站点
     * @return 站点列表
     */
    public List<Station> selectStationList(Station station) {
        // 1. 调用Mapper层查询站点列表数据
        List<Station> list = stationMapper.selectStationList(station);

        // 2. 提取所有站点关联的柜机ID列表
        List<Long> cabinetIdList = list.stream()
                .map(Station::getCabinetId)
                .collect(Collectors.toList());

        // 3. 初始化柜机编号映射关系容器
        Map<Long, String> cabinetIdToCabinetNoMap = new HashMap<>();

        // 4. 查询柜机信息并构建ID到编号的映射关系
        if (!CollectionUtils.isEmpty(cabinetIdList)) {
            // 查询所有相关的柜机信息
            List<Cabinet> cabinetList = cabinetService.list(
                    new LambdaQueryWrapper<Cabinet>().in(Cabinet::getId, cabinetIdList));

            // 构建柜机ID到柜机编号的映射关系
            cabinetIdToCabinetNoMap = cabinetList.stream()
                    .collect(Collectors.toMap(Cabinet::getId, Cabinet::getCabinetNo));
        }

        // 5. 将柜机编号填充到对应的站点对象中
        for (Station item : list) {
            item.setCabinetNo(cabinetIdToCabinetNoMap.get(item.getCabinetId()));
        }

        // 6. 返回最终包含完整信息的站点列表
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public int saveStation(Station station) {
        String provinceName = regionService.getNameByCode(station.getProvinceCode());
        String cityName = regionService.getNameByCode(station.getCityCode());
        String districtName = regionService.getNameByCode(station.getDistrictCode());
        station.setFullAddress(provinceName + cityName + districtName + station.getAddress());
        this.save(station);

        return 1;
    }

    /**
     * 修改站点
     * @param station 站点
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateStation(Station station) {
        String provinceName = regionService.getNameByCode(station.getProvinceCode());
        String cityName = regionService.getNameByCode(station.getCityCode());
        String districtName = regionService.getNameByCode(station.getDistrictCode());
        station.setFullAddress(provinceName + cityName + districtName + station.getAddress());
        this.updateById(station);

        return 1;
    }
}