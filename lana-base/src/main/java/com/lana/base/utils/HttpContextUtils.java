package com.lana.base.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:03
 */
public class HttpContextUtils {


    /**
     * 获取 HTTP Servlet 请求
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        // 强制类型转换安全检查
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    /**
     * 获取 HTTP Servlet 响应
     * @return
     */
    public static HttpServletResponse getHttpServletResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        // 强制类型转换安全检查
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getResponse();
        }
        return null;
    }


    /**
     * 获取请求来源
     * @return
     */
    public static String getOrigin() {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            // 直接返回ORIGIN，增加对返回值的null检查逻辑
            return request.getHeader(HttpHeaders.ORIGIN);
        }
        return null;
    }
}
