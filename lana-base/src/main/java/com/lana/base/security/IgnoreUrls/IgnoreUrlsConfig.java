package com.lana.base.security.IgnoreUrls;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/16 15:12
 */
public class IgnoreUrlsConfig {
    /**
     * 指定被 spring security 忽略的URL
     */
    public static List<String> getPermitList() {
        List<String> list = new ArrayList<>();
        //验证码
        list.add("/sys/captcha/getCaptcha");
        //是否弃用验证码
        list.add("/sys/captcha/enabled");
        //登陆
        list.add("/sys/auth/login");
        //刷新token
        list.add("/sys/auth/token");
        //退出登录
        list.add("/sys/auth/logout");
        //认证码
        list.add("/sys/auth/send/code");
        //文件
        list.add("/upload/**");
        //list.add("/rules/quartz/**");
        //springboot程序监控器
        //list.add("/actuator/**");
        list.add("/v3/api-docs/**");
        list.add("/webjars/**");

        //swagger在线文档，使用的话放开下面的注解
/*      list.add("/swagger/**");
        list.add("/swagger-resources/**");
        list.add("/swagger-ui.html");
        list.add("/swagger-ui/**");
        list.add("/doc.html");*/
        return list;
    }
}
