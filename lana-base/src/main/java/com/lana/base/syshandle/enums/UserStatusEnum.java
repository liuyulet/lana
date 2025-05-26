package com.lana.base.syshandle.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    /**
     * 停用
     */
    DISABLE(0, "停用"),
    /**
     * 正常
     */
    ENABLED(1, "正常");

    private final int value;
    private final String name;

}
