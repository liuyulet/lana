package com.lana.abutment.mqtthandle.initializer.proties;

import lombok.Data;

/**
 * @auther liuyulet
 * @date 2024/9/4 22:07
 */
@Data
public class SslPerties {
    private Boolean enabled;
    private String keystorePath;
    private String keystorePass;
    private String truststorePath;
    private String truststorePass;
    private String clientAuth;

}
