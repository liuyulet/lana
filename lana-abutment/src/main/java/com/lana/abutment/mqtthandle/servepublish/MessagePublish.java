package com.lana.abutment.mqtthandle.servepublish;

import jakarta.annotation.Resource;
import org.dromara.mica.mqtt.codec.MqttQoS;
import org.dromara.mica.mqtt.spring.server.MqttServerTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/6 15:44
 */
@Component
public class MessagePublish {
    @Resource
    private MqttServerTemplate server;


    /**
     * 向该ClientId推送
     * @param clientId
     * @param topic
     * @param body
     * @return
     */
    public boolean publishClientId(String clientId, String topic,String body) {
        server.publish(clientId,topic, body.getBytes(StandardCharsets.UTF_8));
        return true;
    }

    /**
     * 向该ClientId推送不同qos的消息
     * @param clientId
     * @param topic
     * @param body
     * @param qos
     * @return
     */
    public boolean publishClientIdQos(String clientId, String topic, String body, MqttQoS qos) {
        server.publish(clientId,topic, body.getBytes(StandardCharsets.UTF_8),qos);
        return true;
    }

    /**
     * 向该ClientId推送消息并设置保留消息
     * @param clientId
     * @param topic
     * @param body
     * @param retain
     * @return
     */
    public boolean publishClientIdRetain(String clientId, String topic, String body, boolean retain) {
        server.publish(clientId,topic, body.getBytes(StandardCharsets.UTF_8),retain);
        return true;
    }

    /**
     * 向该ClientId推送不同qos的消息并设置保留消息
     * @param clientId
     * @param topic
     * @param body
     * @param retain
     * @return
     */
    public boolean publishClientIdQosRetain(String clientId, String topic, String body, boolean retain) {
        server.publish(clientId,topic, body.getBytes(StandardCharsets.UTF_8),retain);
        return true;
    }



    /**
     * 向topic推送
     * @param body
     * @param topic
     * @return
     */
    public boolean publishTopic(String topic,String body) {
        server.publishAll(topic, body.getBytes(StandardCharsets.UTF_8));
        return true;
    }

    /**
     * 向topic推送不同qos的数据
     * @param topic
     * @param body
     * @param qos
     * @return
     */
    public boolean publishTopicQos(String topic,String body,MqttQoS qos) {
        server.publishAll(topic, body.getBytes(StandardCharsets.UTF_8),qos);
        return true;
    }


    /**
     * 向topic推送数据并设置保留消息
     * @param topic
     * @param body
     * @param retain
     * @return
     */
    public boolean publishTopicRetain(String topic,String body,boolean retain) {
        server.publishAll(topic, body.getBytes(StandardCharsets.UTF_8),retain);
        return true;
    }

    /**
     * 向topic推送不同qos的数据并设置保留消息
     * @param topic
     * @param body
     * @param qos
     * @param retain
     * @return
     */
    public boolean publishTopicQosRetain(String topic,String body,MqttQoS qos,boolean retain) {
        server.publishAll(topic, body.getBytes(StandardCharsets.UTF_8),qos,retain);
        return true;
    }

}
