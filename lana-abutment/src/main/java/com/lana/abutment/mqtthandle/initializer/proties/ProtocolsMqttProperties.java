package com.lana.abutment.mqtthandle.initializer.proties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/8/20 18:36
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt.server")
public class ProtocolsMqttProperties {
    private Boolean enabled;
    private String ip;
    private Integer port;
    private String name;
    private Integer heartbeatTimeout;
    private String readBufferSize;
    private String maxBytesInMessage;
    private AuthProPerties auth;
    private Boolean debug;
    private Boolean statEnable;
    private Integer webPort;
    private Boolean websocketEnable;
    private Boolean httpEnable;
    private HttpBasicAuthPerties httpBasicAuth;
    private SslPerties ssl;
    private Integer linkType;



}
