package com.lana.base.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 存储配置项
 */

@Data
@ConfigurationProperties(prefix = "storage")
public class FileProperties {
    /**
     * 是否开启存储
     */
    private boolean enabled;
    /**
     * 通用配置项
     */
    private StorageConfig config;

    /**
     * Minio配置
     */
    private MinioFileProperties minio;

    @Data
    public static class StorageConfig {
        /**
         * 访问域名
         */
        private String domain;
        /**
         * 配置路径前缀
         */
        private String prefix;

    }

    @Bean
    @ConfigurationProperties(prefix = "storage.minio")
    public MinioFileProperties minioStorageProperties() {
        return new MinioFileProperties();
    }

}

