package com.lana.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther liuyulet
 * @date 2022/9/15 21:11
 */

@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {
    /** 请求的 ip + port */
    private static String endpoint;

    /**访问的key**/
    private static String accesskey;

    /**访问的秘钥**/
    private static String secretkey;

    /**桶名**/
    private static String bucketname;


    /**http请求地址**/
    private static String httppoint;


    public static String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        MinioConfig.endpoint = endpoint;
    }

    public static String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        MinioConfig.accesskey = accesskey;
    }

    public static String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        MinioConfig.secretkey = secretkey;
    }

    public static String getBucketname() {
        return bucketname;
    }

    public void setBucketname(String bucketname) {
        MinioConfig.bucketname = bucketname;
    }

    public static String getHttppoint() {
        return httppoint;
    }

    public void setHttppoint(String httppoint) {
        MinioConfig.httppoint = httppoint;
    }


}
