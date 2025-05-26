package com.lana.base.cacheops;

import com.lana.base.cacheops.prefix.CachePrefix;

/** 缓存
 * @auther liuyulet
 * @date 2024/3/16 12:55
 */
public class CacheKeyBuilder {


    /**
     * 验证码Key
     */
    public static String captchaKey(String key) {

        return CachePrefix.CAPTCHAKEY + key;
    }
    /**
     * 认证信息 Key
     */
    public static String accessTokenKey(String accessToken) {

        return CachePrefix.ACCESSTOKENKEY + accessToken;
    }

    /**
     * 第三方认证信息 Key
     */
    public static String accessThirdTokenkey(String accessToken) {

        return CachePrefix.ACCESSTHIRDTOKENKEY + accessToken;
    }


    /**
     * aviator脚本本地缓存
     */
    public static String aviatorScript(String aviatorScript) {

        return CachePrefix.AVIATORSCRIPT + aviatorScript;
    }

    /**
     * 设备物模型
     */
    public static String deviceMode(String deviceNub) {

        return CachePrefix.DEVICEMODE+deviceNub;
    }

    /**
     * 设备功能
     */
    public static String deviceControl(String deviceNub) {

        return CachePrefix.DEVICECONTROL+deviceNub;
    }


    /**
     * 设备功能
     */
    public static String deviceActionCommands(String commandsNub) {

        return CachePrefix.DEVICEACTIONCOMMANDS+commandsNub;
    }



    /**
     * 设备分组
     */
    public static String deviceGroup(String groupNub) {

        return CachePrefix.DEVICEGROUP+groupNub;
    }

    /**
     * 设备--规则关联关系（监听规则）
     */
    public static String deviceIdRule(String deviceNub) {

        return CachePrefix.DEVICEIDRULE+deviceNub;
    }


    /**
     * 设备--定时规则关联关系（采集规则）
     */
    public static String deviceIdRuleJon(String deviceNub) {

        return CachePrefix.DEVICEIDRULEJOB+deviceNub;
    }

    /**
     * 这个规则下有多少设备，用于后期的定时采集指令下发（采集规则）
     */
    public static String rulesJobPushDevice(String ruleId) {

        return CachePrefix.RULESJOBPUSHDEVICE+ruleId;
    }



    /**
     * 设备--定时规则关联关系（采集规则）
     */
    public static String actionMap(Long actionMapNub) {

        return CachePrefix.ACTIONMAP+actionMapNub;
    }


    public static String initActionMap(String actionMapNub) {

        return CachePrefix.ACTIONMAP+actionMapNub;
    }



    /**
     * mqtt json数据缓存
     */
    public static String mqttScript() {

        return CachePrefix.MQTTSCRIPT;
    }

    /**
     * 日志 key
     * @return
     */
    public static String logKey() {

        return CachePrefix.LOGKEY;
    }

}
