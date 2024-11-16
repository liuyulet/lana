package com.lana.base.file.config;

import com.lana.base.file.service.FileService;
import com.lana.base.file.service.MinioFileService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储配置文件
 */
@Configuration
@EnableConfigurationProperties(FileProperties.class)
@ConditionalOnProperty(prefix = "storage", value = "enabled")
public class FileConfiguration {
    @Bean
    public FileService storageService(FileProperties properties) {
        try {
            return new MinioFileService(properties);
        } catch (Exception e) {
            throw new RuntimeException("无法创建 MinioStorageService", e);
        }
    }
}
