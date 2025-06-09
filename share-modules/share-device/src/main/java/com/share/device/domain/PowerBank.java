package com.share.device.domain;

import com.share.common.core.web.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 充电宝
 * Author: guo'cha
 * CreateTime: 2025/6/4
 * Project: share-parent
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "充电宝")
public class PowerBank extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 充电宝编号
     */
    @Schema(description = "充电宝编号")
    private String powerBankNo;

    /**
     * 电量
     */
    @Schema(description = "电量")
    private BigDecimal electricity;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

}
