package com.lana.base.syshandle.xss.proties;

import com.lana.base.security.IgnoreUrls.IgnoreUrlsConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @auther liuyulet
 * @date 2024/3/16 14:13
 */
@Data
@ConfigurationProperties(prefix = "lana.xss")
public class XssProperties {
    /**
     * 是否开启 XSS
     */
    private boolean enabled;

    /**
     * 排除的URL列表,默认都不排除，如果要排除，可以直接将排除的路径添加到该列表中
     */
    private List<String> ignorePaths = IgnoreUrlsConfig.getPermitList();
    //private List<String> ignorePaths = Collections.emptyList();

}
