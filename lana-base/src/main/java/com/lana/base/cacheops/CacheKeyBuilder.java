package com.lana.base.cacheops;

import com.lana.base.cacheops.prefix.RedisCachePrefix;

/** 缓存
 * @auther liuyulet
 * @date 2024/3/16 12:55
 */
public class CacheKeyBuilder {


    /**
     * 验证码Key
     */
    public static String captchaKey(String key) {

        return RedisCachePrefix.CAPTCHAKEY + key;
    }
    /**
     * 认证信息 Key
     */
    public static String accessTokenKey(String accessToken) {

        return RedisCachePrefix.ACCESSTOKENKEY + accessToken;
    }

    /**
     * 第三方认证信息 Key
     */
    public static String accessThirdTokenkey(String accessToken) {

        return RedisCachePrefix.ACCESSTHIRDTOKENKEY + accessToken;
    }


    /**
     * aviator脚本本地缓存
     */
    public static String aviatorScript(String aviatorScript) {

        return RedisCachePrefix.AVIATORSCRIPT + aviatorScript;
    }

    /**
     * 设备物模型
     */
    public static String deviceMode(String deviceNub) {

        return RedisCachePrefix.DEVICEMODE+deviceNub;
    }


    /**
     * mqtt json数据缓存
     */
    public static String mqttScript() {

        return RedisCachePrefix.MQTTSCRIPT;
    }

    /**
     * 日志 key
     * @return
     */
    public static String logKey() {

        return RedisCachePrefix.LOGKEY;
    }

}
