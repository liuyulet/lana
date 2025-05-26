package com.lana.base.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther liuyulet
 * @date 2024/3/16 13:01
 */
@Configuration
public class SwaggerConfig {

    // todo 后续再设置自动分组吧....
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("系统设置")
                .pathsToMatch("/sys/**")
                .packagesToScan("com.lana.system")
                .build();
    }

    @Bean
    public GroupedOpenApi deviceApi() {
        return GroupedOpenApi.builder()
                .group("设备管理")
                .pathsToMatch("/devices/**")
                .packagesToScan("com.lana.device")
                .build();
    }
    @Bean
    public GroupedOpenApi abutmentApi() {
        return GroupedOpenApi.builder()
                .group("接入管理")
                .pathsToMatch("/abutment/**")
                .packagesToScan("com.lana.abutment")
                .build();
    }
    @Bean
    public GroupedOpenApi rulesApi() {
        return GroupedOpenApi.builder()
                .group("规则设置")
                .pathsToMatch("/rules/**")
                .packagesToScan("com.lana.rules")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact();
        contact.setName("Liuyulet");
        return new OpenAPI().info(new Info()
                .title("Lana接口文档")
                .description("一款基于java语言的物联网云平台，用于设备接入、设备展示、设备反控等操作的平台。")
                .contact(contact)
                .version("1.0")
                .termsOfService("Apache-2.0 license")
                .license(new License().name("Apache-2.0 license").url("Not ready, please wait")));
    }
}
