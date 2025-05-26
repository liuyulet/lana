package com.lana.base.syshandle.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @auther liuyulet
 * @date 2024/3/16 13:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LanaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    public LanaException(String msg) {
        super(msg);
        this.code = LanaExceptionCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }
    public LanaException(String msg, Throwable e) {
        super(msg, e);
        this.code = LanaExceptionCode.INTERNAL_SERVER_ERROR.getCode();
        this.msg = msg;
    }
}
