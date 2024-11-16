package com.lana.base.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP信息
 * @auther liuyulet
 * @date 2024/3/16 13:57
 */

@Slf4j
public class IpInformationUtils {

    /**
     * 获取客户端IP地址
     */
    /**
     * 获取客户端IP地址。
     * 本方法旨在通过检查多个HTTP头来确定客户端的IP地址，这是因为客户端的IP地址可能由于网络架构（如反向代理、负载均衡器等）的不同而出现在不同的HTTP头中。
     * 具体来说，方法首先检查“x-forwarded-for”头，这是最常见的方式，如果不存在，则尝试其他几个常见的HTTP头。
     * 如果所有尝试都失败了，方法将回退到使用Servlet API提供的{@code request.getRemoteAddr()}来获取IP地址。
     *
     * @param request 代表HTTP请求的HttpServletRequest对象。
     * @return 客户端的IP地址。如果无法确定，则返回“unknown”。
     */
    public static String getIpAddr(HttpServletRequest request) {
        // 如果请求对象为空，直接返回“unknown”
        if (request == null) {
            return "unknown";
        }
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("getIpAddress exception:", e);
                }
                assert inet != null;
                ipAddress = inet.getHostAddress();
            }
        }
        return ipAddress;

    }

}
