package com.lana.base.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.lana.base.mybatis.handler.FillMetaObjectHandler;
import com.lana.base.mybatis.interceptor.DataScopeInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:16
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new DataScopeInnerInterceptor());
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }


    // 拼接默认信息
    @Bean
    public FillMetaObjectHandler fieldMetaObjectHandler(){
        return new FillMetaObjectHandler();
    }

}
