package com.lana.system.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther liuyulet
 * @date 2024/3/30 15:23
 */
@Data
@Schema(description = "菜单")
public class SysMetaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Boolean hidden;
    private Boolean affix;
    private String icon;
    private String type;
    private Boolean hiddenBreadcrumb;
    private Boolean active;
    private String color;
    private Boolean fullpage;
    private String role;
}
