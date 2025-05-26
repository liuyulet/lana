package com.lana.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单api权限管理
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menus_api")
public class SysMenusApiEntity extends BaseEntity {
    private Long menuId;
    private String code;
    private String url;
}