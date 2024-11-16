package com.lana.abutment.mqtthandle.servelistener;

import com.alibaba.fastjson.JSONObject;
import com.lana.base.cacheops.CacheKeyBuilder;
import com.lana.base.cacheops.CacheOps;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.codec.MqttPublishMessage;
import net.dreamlu.iot.mqtt.codec.MqttQoS;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/6 15:39
 */
@Slf4j
@Service
public class MessageListener implements IMqttMessageListener {

    @Resource
    private CacheOps cacheOps;

    ExecutorService executor = Executors.newFixedThreadPool(10); // 创建固定大小的线程池
    /**
     * 初版默认走mqtt监听
     */
    @Override
    public void onMessage(ChannelContext context, String clientId, String topic, MqttQoS qoS, MqttPublishMessage message) {
        executor.submit(() -> {
            // todo 判断是否使用的是mqtt监听，后续增加AMQP、kafka队列
            if(true){
                String key = CacheKeyBuilder.mqttScript();
                // 走redis缓存队列
                String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
                JSONObject jsonObject = JSONObject.parseObject(payload);
                jsonObject.put("deviceId",topic.substring(2));
                cacheOps.leftPush(key, jsonObject, CacheOps.NO_EXPIRE);
            }else {

            }
        });
    }

}

