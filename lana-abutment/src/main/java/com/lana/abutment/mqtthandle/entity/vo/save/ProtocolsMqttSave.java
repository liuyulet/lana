package com.lana.abutment.mqtthandle.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/30 13:14
 */
@Data
@Schema(description = "Mqtt协议")
public class ProtocolsMqttSave {

    @Schema(description = "mqtt IP地址（所部署的服务器的ip）")
    private String ip;
    /**
     * mqtt名称
     */
    @Schema(description = "mqtt名称,展示使用")
    private String mqttName;
    /**
     * 1:本地mqtt，2：代理mqtt（emqx）
     */
    @Schema(description = " 1:本地mqtt，2：代理mqtt（emqx）")
    private Integer mqttType;
    /**
     * tcp端口，默认：1883
     */
    @Schema(description = "tcp端口，默认：1883")
    private String tcpPort;
    /**
     * http、websocket 端口，默认：8083
     */
    @Schema(description = "http、websocket 端口，默认：8083")
    private String websocketPort;
    /**
     * mqtttcp是否开启认证1是0否
     */
    @Schema(description = "mqtttcp是否开启认证1是0否")
    private Integer authEnable;
    /**
     * mqtt 认证用户名
     */
    @Schema(description = "mqtt 认证用户名")
    private String username;
    /**
     * mqtt 认证密码
     */
    @Schema(description = "mqtt 认证密码")
    private String password;
    /**
     * http-api是否开启,1是0否
     */
    @Schema(description = "http-api是否开启,1是0否")
    private Integer httpEnable;
    /**
     * http-api如果开启，则设置该选项是否开启 http basic auth，默认： false（0）
     */
    @Schema(description = "http-api如果开启，则设置该选项是否开启 http basic auth，默认： false（0）")
    private Integer httpBasicAuth;
    /**
     * http basic auth 用户名
     */
    @Schema(description = "http basic auth 用户名")
    private String httpBasicUsername;
    /**
     * http basic auth 密码
     */
    @Schema(description = "http basic auth 密码")
    private String httpBasicPassword;
    /**
     * 是否开启 ssl 认证,1是0否
     */
    @Schema(description = "是否开启 ssl 认证,1是0否")
    private Integer sslEnabled;
    /**
     * 必须参数：ssl keystore 目录
     */
    @Schema(description = "必须参数：ssl keystore 目录")
    private String keystorePath;
    /**
     * 必选参数：ssl keystore 密码
     */
    @Schema(description = "必选参数：ssl keystore 密码")
    private String keystorePass;
    /**
     * 是否需要客户端认证（双向认证），默认：NONE（不需要），REQUIRE（需要）
     */
    @Schema(description = "是否需要客户端认证（双向认证），默认：NONE（不需要），REQUIRE（需要）")
    private String clientAuth;
    /**
     * 可选参数：ssl 双向认证 truststore 目录
     */
    @Schema(description = "可选参数：ssl 双向认证 truststore 目录")
    private String truststorePath;
    /**
     * 可选参数：ssl 双向认证 truststore 密码
     */
    @Schema(description = "可选参数：ssl 双向认证 truststore 密码")
    private String truststorePass;
    /**
     * mqtt是否启用1是0否
     */
    @Schema(description = "mqtt是否启用1是0否")
    private Integer mqttEnabled;

    /**
     * 连接方式
     */
    @Schema(description = "mqtt是否启用1是0否")
    private Integer linkType;
}
