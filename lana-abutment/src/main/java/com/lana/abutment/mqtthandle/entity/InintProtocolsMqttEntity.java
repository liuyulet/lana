package com.lana.abutment.mqtthandle.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lana.base.utils.DateUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/20 18:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("protocols_mqtt")
public class InintProtocolsMqttEntity {
    private Long id;
    /**
     * ip
     */
    private String ip;
    /**
     * mqtt名称
     */
    private String mqttName;
    /**
     * 1:本地mqtt，2：代理mqtt（emqx）
     */
    private Integer mqttType;
    /**
     * tcp端口，默认：1883
     */
    private String tcpPort;
    /**
     * http、websocket 端口，默认：8083
     */
    private String websocketPort;
    /**
     * mqtttcp是否开启认证1是0否
     */
    private Integer authEnable;
    /**
     * mqtt 认证用户名
     */
    private String username;
    /**
     * mqtt 认证密码
     */
    private String password;
    /**
     * http-api是否开启,1是0否
     */
    private Integer httpEnable;
    /**
     * http-api如果开启，则设置该选项是否开启 http basic auth，默认： false（0）
     */
    private Integer httpBasicAuth;
    /**
     * http basic auth 用户名
     */
    private String httpBasicUsername;
    /**
     * http basic auth 密码
     */
    private String httpBasicPassword;
    /**
     * 是否开启 ssl 认证,1是0否
     */
    private Integer sslEnabled;
    /**
     * 必须参数：ssl keystore 目录
     */
    private String keystorePath;
    /**
     * 必选参数：ssl keystore 密码
     */
    private String keystorePass;
    /**
     * 是否需要客户端认证（双向认证），默认：NONE（不需要），REQUIRE（需要）
     */
    private String clientAuth;
    /**
     * 可选参数：ssl 双向认证 truststore 目录
     */
    private String truststorePath;
    /**
     * 可选参数：ssl 双向认证 truststore 密码
     */
    private String truststorePass;
    /**
     * mqtt是否启用1是0否
     */
    private Integer mqttEnabled;

    /**
     * 连接方式
     */
    private Integer linkType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;
    /**
     * 创建人
     */
    private String creatorName;
    /**
     * 修改人
     */
    private String updaterName;


}
