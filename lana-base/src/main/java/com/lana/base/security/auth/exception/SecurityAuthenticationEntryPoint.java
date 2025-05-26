package com.lana.base.security.auth.exception;

import com.lana.base.syshandle.result.LanaResult;
import com.lana.base.syshandle.exception.LanaExceptionCode;
import com.lana.base.security.IgnoreUrls.WhiteListConfig;
import com.lana.base.utils.HttpContextUtils;
import com.lana.base.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:33
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // 设置响应头逻辑
        setResponseHeaders(response);
        response.getWriter().print(JsonUtils.toJsonString(LanaResult.error(LanaExceptionCode.UNAUTHORIZED)));
    }

    private void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        String origin = HttpContextUtils.getOrigin();
        // 白名单校验
        if (isValidOrigin(origin)) {
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            // 对于非法的来源，可以选择不响应，或者使用通用的错误提示
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
    }

    /**
     * 暂不使用
     * @param origin
     * @return
     */
    private boolean isValidOrigin(String origin) {
        //   读取白名单
        String[] validDomains = WhiteListConfig.getWhiteList();
        if(validDomains != null && validDomains.length > 0){
            for (String domain : validDomains) {
                if (origin.endsWith(domain)) {
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }

    }





}
