package com.lana.base.cacheops.prefix;

/**
 * @auther liuyulet
 * @date 2024/3/16 12:46
 */
public interface CachePrefix {

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
     * 设备功能，用于在设备定时任务中，定时采集设备的时候，避免频繁地查询数据库。
     */
    String DEVICECONTROL = "lana:deviceControl:";

    /**
     * 设备功能，用于在动作触发的时候，根据设备的功能id进行直接检索对应的动作指令，避免频繁的检索数据库。
     */
    String DEVICEACTIONCOMMANDS = "lana:deviceActionCommands:";


    /**
     * 设备分组，用于分组控制的时候的检索
     */
    String DEVICEGROUP = "lana:deviceGroup:";


    /**
     * 设备--规则关联关系（监听规则）
     */
    String DEVICEIDRULE = "lana:deviceIdRule:";

    /**
     * 设备--规则关联关系（定时规则）
     */
    String DEVICEIDRULEJOB = "lana:deviceIdRuleJob:";

    /**
     * 规则id---设备id关联关系（定时规则）
     */
    String RULESJOBPUSHDEVICE = "lana:rulesJobPushDevice:";

    /**
     * 规则--动作信息
     */
    String ACTIONMAP = "actionMap";

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

