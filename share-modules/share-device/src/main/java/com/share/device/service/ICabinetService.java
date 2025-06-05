package com.share.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.device.domain.Cabinet;
import com.share.device.domain.CabinetType;

import java.util.List;

/**
 * 充电柜机服务接口
 * 提供对充电柜机的增删改查业务操作
 */
public interface ICabinetService extends IService<Cabinet> {

    /**
     * 查询充电宝柜机列表
     * @param cabinet 查询条件（支持按名称、状态等字段过滤）
     * @return 柜机信息集合（包含类型信息）
     */
    List<CabinetType> selectCabinetList(Cabinet cabinet);

    /**
     * 新增充电柜机
     * @param cabinet 充电柜机信息
     * @return 受影响行数
     */
    int saveCabinet(Cabinet cabinet);

    /**
     * 修改充电柜机
     * @param cabinet 充电柜机信息
     * @return 受影响行数
     */
    int updateCabinet(Cabinet cabinet);

    /**
     * 删除充电柜机
     * @param idList 充电柜机信息
     * @return 受影响行数
     */
    int removeCabinet(List<Long> idList);

    /**
     * 搜索未使用的充电柜机
     * @param keyword 关键字
     * @return 充电柜机信息集合
     */
    List<Cabinet> searchNoUseList(String keyword);

}