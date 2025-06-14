package com.share.device.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "站点")
public class StationVO {

    @Schema(description = "站点ID")
    private Long id;

    @Schema(description = "站点名称")
    private String name;

    @Schema(description = "站点图片地址")
    private String imageUrl;

    @Schema(description = "营业时间")
    private String businessHours;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "完整地址")
    private String fullAddress;

    @Schema(description = "是否可用")
    private String isUsable;

    @Schema(description = "是否可还")
    private String isReturn;

    @Schema(description = "举例")
    private Double distance;

    @Schema(description = "费用规则")
    private String feeRule;

}
