package com.lana.base.operatelog.aspect;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.lana.base.operatelog.annotations.OptLog;
import com.lana.base.operatelog.service.OperateLogService;
import com.lana.base.utils.HttpContextUtils;
import com.lana.base.utils.IpInformationUtils;
import com.lana.base.utils.JsonUtils;
import com.lana.base.operatelog.entity.OptLogEntity;
import com.lana.base.security.token.user.SecurityUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * 操作日志，切面处理类
 * @auther liuyulet
 * @date 2024/3/16 14:25
 */
@Aspect
@Component
@AllArgsConstructor
public class OperateLogAspect {

    private final OperateLogService operateLogService;

    @Around("@annotation(optLog)")
    public Object around(ProceedingJoinPoint joinPoint, OptLog optLog) throws Throwable {
        try {
            //执行方法
            Object result = joinPoint.proceed();
            //保存日志
            saveLog(joinPoint, optLog, LocalDateTime.now());
            return result;
        } catch (Exception e) {
            //保存日志
            saveLog(joinPoint, optLog, LocalDateTime.now());
            throw e;
        }
    }


    private void saveLog(ProceedingJoinPoint joinPoint, OptLog optLog, LocalDateTime startTime) {
        OptLogEntity log = new OptLogEntity();

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (request != null) {
            log.setIp(IpInformationUtils.getIpAddr(request));
            log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            log.setReqUri(request.getRequestURI());
            log.setReqMethod(request.getMethod());
        }
        log.setDuration((int) LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis());
        log.setUserId(SecurityUser.getUser().getId());
        log.setRealName(SecurityUser.getUser().getRealName());
        log.setOperateType(optLog.type()[0].getValue());
        log.setModule(optLog.module());
        log.setName(optLog.name());
        if (StrUtil.isBlank(log.getModule())) {
            Tag tag = ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(Tag.class);
            if (tag != null) {
                log.setModule(tag.name());
            }
        }
        if (StrUtil.isBlank(log.getName())) {
            Operation operation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Operation.class);
            if (operation != null) {
                log.setName(operation.summary());
            }
        }

        if (joinPoint.getArgs().length > 0) {
            if (joinPoint.getArgs()[0] instanceof MultipartFile) {
                log.setReqParams("file");
            } else {
                log.setReqParams(joinPoint.getArgs().length > 0 ? JsonUtils.toJsonString(joinPoint.getArgs()) : null);
            }
        }
        // 保存操作日志
        operateLogService.saveLog(log);
    }



}
