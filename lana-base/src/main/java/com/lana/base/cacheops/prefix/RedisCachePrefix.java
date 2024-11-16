package com.lana.base.cacheops.prefix;

/**
 * @auther liuyulet
 * @date 2024/3/16 12:46
 */
public interface RedisCachePrefix {

    /**
     * captcha缓存
     */
    String CAPTCHAKEY = "lana:captcha:";

    /**
     * token缓存
     */
    String ACCESSTOKENKEY = "lana:token:";

    /**
     * 脚本缓存
     */
    String MQTTSCRIPT = "lana:mqttScript:";

    /**
     * 设备物模型
     */
    String DEVICEMODE = "lana:deviceMode:";

    /**
     * 脚本缓存
     */
    String AVIATORSCRIPT = "lana:aviatorscript:";

    /**
     * 第三方认证缓存，后期作为南向、北向的认证，其实绝大多数情况都会用于北向操作，南向更多的可能会基于mqtt来实现
     */
    String ACCESSTHIRDTOKENKEY = "lana:others:";

    /**
     * log缓存
     */
    String LOGKEY = "lana:log";
}

