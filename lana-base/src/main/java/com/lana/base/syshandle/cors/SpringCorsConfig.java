package com.lana.base.syshandle.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * @auther liuyulet
 * @date 2024/3/16 12:59
 */
@Configuration
public class SpringCorsConfig {

    /**
     * 创建并返回一个CorsFilter实例。
     * @return CorsFilter 返回一个CorsFilter实例，用于处理跨域请求。
     */
    @Bean
    public CorsFilter corsFilter() {
        // 创建一个基于URL的跨域配置源
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建一个跨域配置对象
        final CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 允许所有的请求头
        corsConfiguration.setAllowCredentials(true); // 允许请求带有凭证
        corsConfiguration.addAllowedHeader("*");
        // 允许所有的请求来源
        corsConfiguration.addAllowedOriginPattern("*");
        // 允许GET、POST、PUT、DELETE的请求方法
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("PUT");
        corsConfiguration.addAllowedMethod("DELETE");

        // 将跨域配置注册到URL基础的跨域配置源中
        source.registerCorsConfiguration("/**", corsConfiguration);

        // 根据配置创建并返回CorsFilter实例
        return new CorsFilter(source);
    }
}

