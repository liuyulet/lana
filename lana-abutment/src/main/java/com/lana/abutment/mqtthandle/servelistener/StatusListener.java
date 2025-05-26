package com.lana.abutment.mqtthandle.servelistener;

import com.lana.abutment.mqtthandle.dao.DeviceStatusDao;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.spring.server.event.MqttClientOfflineEvent;
import org.dromara.mica.mqtt.spring.server.event.MqttClientOnlineEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 设备上下线监听
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/6 16:00
 */
@Slf4j
@Component
public class StatusListener {

    @Resource
    private DeviceStatusDao deviceStatusDao;

    @EventListener
    public void online(MqttClientOnlineEvent event) {

        deviceStatusDao.updataStatus(Long.valueOf(event.getClientId().substring(2)),1);
    }

    @EventListener
    public void offline(MqttClientOfflineEvent event) {

        deviceStatusDao.updataStatus(Long.valueOf(event.getClientId().substring(2)),0);
    }

}
