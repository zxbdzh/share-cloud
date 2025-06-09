package com.share.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.share.device.domain.PowerBank;

import java.util.List;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/4
 * Project: share-parent
 */
public interface IPowerBankService extends IService<PowerBank> {

    /**
     * 查询充电宝列表
     * @param powerBank 充电宝
     * @return 充电包列表
     */
    List<PowerBank> selectPowerBankList(PowerBank powerBank);

    PowerBank getByPowerBankNo(String powerBankNo);

    /**
     * 新增充电宝
     * @param powerBank
     * @return
     */
    int savePowerBank(PowerBank powerBank);

    int updatePowerBank(PowerBank powerBank);
}
