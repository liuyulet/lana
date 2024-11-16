package com.lana.base.syshandle.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @auther liuyulet
 * @date 2024/3/16 13:37
 */
@Getter
@AllArgsConstructor
public enum LanaExceptionCode {

    UNAUTHORIZED(401, "未授权，请登陆授权后访问"),
    FORBIDDEN(403, "没有权限，禁止访问"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试"),
    INVALID_PARAMETER(300, "无效参数");


    private final int code;
    private final String msg;
}
