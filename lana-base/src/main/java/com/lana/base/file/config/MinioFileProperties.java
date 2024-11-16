package com.lana.base.file.config;

import lombok.Data;

/**
 * Minio存储配置项

 */
@Data
public class MinioFileProperties {
    //地址
    private String endPoint;
    //访问密钥
    private String accessKey;
    //秘钥
    private String secretKey;
    //桶名
    private String bucketName;
}
