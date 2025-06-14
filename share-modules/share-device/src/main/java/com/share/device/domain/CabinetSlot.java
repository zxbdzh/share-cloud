package com.share.device.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.share.common.core.web.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class CabinetSlot extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "柜机id")
    private Long cabinetId;

    @Schema(description = "插槽编号")
    private String slotNo;

    @Schema(description = "充电宝id")
    @TableField(updateStrategy = FieldStrategy.IGNORED) // 指定null时, 不更新此字段
    private Long powerBankId;

    /* (1占用 0空闲 2锁定) */
    @Schema(description = "状态")
    private String status;

    @Schema(description = "充电宝")
    @TableField(exist = false)
    private PowerBank powerBank;

}
