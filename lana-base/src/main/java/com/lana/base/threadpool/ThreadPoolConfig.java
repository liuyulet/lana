package com.lana.base.threadpool;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2025/5/17 22:22
 */

@Configuration
public class ThreadPoolConfig {

    @Resource
    private ThreadPoolProperties props;

    /**
     * Redis Stream 消费专用线程池
     */
    @Bean("redisStreamTaskExecutor")
    @ConditionalOnProperty(name = "lana.rule-action.queue-type", havingValue = "REDIS")
    public ExecutorService redisStreamTaskExecutor() {
        return new ThreadPoolExecutor(
                props.getRedisStreamCoreSize(),
                props.getRedisStreamMaxSize(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(props.getRedisStreamQueueCapacity()),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    /**
     * MQTT 消息异步转发 Redis Stream 的线程池
     */
    @Bean("mqttMessageTaskExecutor")
    public ExecutorService mqttMessageTaskExecutor() {
        return new ThreadPoolExecutor(
                props.getMqttMessageCoreSize(),
                props.getMqttMessageCoreSize(), // max = core
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(props.getMqttMessageQueueCapacity()),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 规则引擎动作执行线程池（如调用API、发邮件）
     */
    @Bean("ruleActionTaskExecutor")
    public ExecutorService ruleActionTaskExecutor() {
        return new ThreadPoolExecutor(
                props.getRuleActionCoreSize(),
                props.getRuleActionCoreSize(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(props.getRuleActionQueueCapacity()),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
