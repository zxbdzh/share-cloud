package com.share.device.domain;

import com.share.common.core.web.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "柜机类型")
public class CabinetType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @Schema(description = "总插槽数量")
    @NotNull(message = "总插槽数量不能为空")
    private Integer totalSlots;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "状态")
    private String status;

}
