package com.lana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举
 */
@Getter
@AllArgsConstructor
public enum HandleTypeEnum {
    /**
     * 新增
     */
    ADDITIONAL(1),
    /**
     * 修改
     */
    MODIFICATION(2);


    private final Integer value;

}