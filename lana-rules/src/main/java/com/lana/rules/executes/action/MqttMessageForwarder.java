package com.lana.rules.executes.action;

import org.springframework.stereotype.Component;

/**MQTT 消息转发器
 * @author liuyulet
 * @create 2025/5/19 16:28
 */
@Component
public class MqttMessageForwarder {
    public void forward(String topic, String payload) {
        // 模拟 MQTT 转发
        System.out.println("MQTT 发送至 [" + topic + "]：" + payload);
        // 实际使用 MqttTemplate 或其他客户端发送
    }
}
