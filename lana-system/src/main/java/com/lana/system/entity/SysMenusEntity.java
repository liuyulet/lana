package com.lana.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理

 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menus")
public class SysMenusEntity extends BaseEntity {
    private Long pid;
    private String name;
    private String path;
    private String component;
    private String redirect;
    private Integer sort;
    private String code;
    private String url;

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