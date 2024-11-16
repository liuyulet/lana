package com.lana.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理

 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuAllEntity {
    private Long id;
    private Long pid;
    private String name;
    private String path;
    private String component;
    private String redirect;
    private Integer sort;

    private Long menu_id;
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