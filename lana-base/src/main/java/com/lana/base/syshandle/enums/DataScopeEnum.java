package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举
 */
@Getter
@AllArgsConstructor
public enum DataScopeEnum {
    /**
     * 全部数据
     */
    ALL(0),
    /**
     * 所在部门及子级可见
     */
    ORGALL(1),
    /**
     * 本机构数据
     */
    ORGONLY(2),
    /**
     * 本人数据
     */
    MYSELF(3);

    private final Integer value;

}