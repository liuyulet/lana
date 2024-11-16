package com.lana.abutment.initializer;

import com.lana.abutment.mqtthandle.initializer.proties.ProtocolsMqttProperties;
import com.lana.abutment.mqtthandle.service.ProtocolsMqttService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @auther liuyulet
 * @date 2024/9/4 21:47
 */
@Component
@Slf4j
public class MqttInitializer {
    @Resource
    private ProtocolsMqttProperties protocolsMqttProperties;
    @Resource
    private ProtocolsMqttService protocolsMqttService;

    @PostConstruct
    public void initialize() {
        // 读取配置文件中的属性
        log.info("开始将配置文件中的mqtt信息初始化到数据库中......");
        protocolsMqttService.initializeMqtt(protocolsMqttProperties);
        log.info("初始化完毕......");

        log.info("开始初始化已有的设备物模型到缓存中......");
        protocolsMqttService.initializeCache();
        log.info("初始化完毕......");

        // todo 待处理初始化规则脚本文件与初始化td数据库表结构。
        //log.info("开始初始化根据设备生成TD数据库表结构......");
        //protocolsMqttService.initializeCache();
        //log.info("初始化完毕......");

        //log.info("开始初始化AS规则脚本文件......");
        //protocolsMqttService.initializeCache();
        //log.info("初始化完毕......");

    }
}
