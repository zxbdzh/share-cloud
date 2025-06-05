package com.share.device.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.share.common.core.web.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "充电宝柜机")
public class Cabinet extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "柜机编号")
    private String cabinetNo;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "类别id")
    private Long cabinetTypeId;

    @Schema(description = "总插槽数量")
    private Integer totalSlots;

    @Schema(description = "空闲插槽数量")
    private Integer freeSlots;

    @Schema(description = "已使用插槽数量")
    private Integer usedSlots;

    @Schema(description = "可用充电宝数量")
    private Integer availableNum;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "当前位置id")
    private Long locationId;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "分类名称")
    @TableField(exist = false)
    private String cabinetTypeName;

}
