package com.lana.common.exception;

import com.lana.common.utils.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @auther liuyulet
 * @date 2022/9/8 23:20
 */
@RestControllerAdvice
public class LExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(LException.class)
    public Result handleLException(LException e){
        Result r = new Result();
        r.setCode(e.getCode());
        r.setMessage(e.getMessage());

        return r;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.error(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return Result.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return Result.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        logger.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }
}

