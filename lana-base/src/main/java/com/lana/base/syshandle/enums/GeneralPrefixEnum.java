package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/10/15 10:07
 */
@Getter
@AllArgsConstructor
public enum GeneralPrefixEnum {

    /**
     * 设备前缀
     */
    DEVICE_PREFIX("SB"),

    /**
     * 表名前缀
     */
    TABLE_PREFIX("sb"),

    /**
     * 规则前缀
     */
    RULE_PREFIX("rule"),
    /**
     * 设备主题前缀
     */
    DEVICE_TOPIC_PREFIX("/SB"),
    /**
     * 边缘计算前缀
     */
    EDGE_PREFIX("BY"),
    /**
     * 边缘计算主题前缀
     */
    EDGE_TOPIC_PREFIX("/BY"),

    /**
     * 脚本后缀
     */
    AVIATORSCRIPT_SUFFIX(".av"),

    /**
     * 脚本Cache名称
     */
    AVIATORSCRIPT_PREFIX("hello");


    private final String value;
}
