package com.share.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.device.domain.Station;

import java.util.List;

public interface IStationService extends IService<Station> {

    /**
     * 查询站点列表
     * @param station 查询参数
     * @return 站点列表
     */
    List<Station> selectStationList(Station station);

    /**
     * 保存站点
     * @param station
     * @return
     */
    int saveStation(Station station);

    /**
     * 修改站点
     * @param station
     * @return
     */
    int updateStation(Station station);

    int setData(Station station);
}
