package com.lana.base.security.IgnoreUrls;

/**
 * @auther liuyulet
 * @date 2024/3/16 15:12
 */
public class WhiteListConfig {
    /**
     * 指定被 spring security 忽略的URL
     */
    public static String[] getWhiteList() {
        //不配置默认不做过滤
        String[] validDomains = {
               /* "https://example.com",
                "https://other-safe-domain.com"*/
        };
        return validDomains;
    }
}
