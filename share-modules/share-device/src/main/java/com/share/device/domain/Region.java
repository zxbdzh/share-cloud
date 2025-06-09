package com.share.device.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.share.common.core.web.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * Author: guo'cha
 * CreateTime: 2025/6/9
 * Project: share-parent
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "地区信息")
public class Region extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 地区编码
     */
    @Schema(description = "地区编码")
    private String code;

    /**
     * 上级地区编码
     */
    @Schema(description = "上级地区code")
    private String parentCode;

    /**
     * 地区名称
     */
    @Schema(description = "地区名称")
    private String name;

    /**
     * 地区级别
     */
    @Schema(description = "地区级别")
    private Long level;

    /**
     * 是否有子级
     */
    @TableField(exist = false)
    private Boolean hasChildren;
}
