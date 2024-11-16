package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 超级管理员枚举
 */
@Getter
@AllArgsConstructor
public enum SuperAdminEnum {
    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");

    private final Integer value;
    private final String name;

}