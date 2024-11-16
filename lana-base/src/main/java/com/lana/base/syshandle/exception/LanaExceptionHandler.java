package com.lana.base.syshandle.exception;

import com.lana.base.syshandle.result.LanaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * @auther liuyulet
 * @date 2024/3/16 13:48
 */
@Slf4j
@RestControllerAdvice
public class LanaExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(LanaException.class)
    public LanaResult<String> handleException(LanaException ex) {

        return LanaResult.error(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public LanaResult<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return LanaResult.error(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public LanaResult<String> handleAccessDeniedException(Exception ex) {

        return LanaResult.error(LanaExceptionCode.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public LanaResult<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return LanaResult.error(LanaExceptionCode.INTERNAL_SERVER_ERROR);
    }
}
