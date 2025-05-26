package com.lana.base.threadpool;

import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2025/5/17 22:33
 */
@Slf4j
@Component
public class GracefulShutdownHook implements DisposableBean {
    @Resource(name = "redisStreamTaskExecutor")
    private ExecutorService redisStreamExecutor;

    @Resource(name = "mqttMessageTaskExecutor")
    private ExecutorService mqttMessageExecutor;

    @Resource(name = "ruleActionTaskExecutor")
    private ExecutorService ruleActionExecutor;

    @Override
    public void destroy() throws Exception {
        shutdownExecutorIfNotNull(redisStreamExecutor, "Redis Stream");
        shutdownExecutorIfNotNull(mqttMessageExecutor, "MQTT Message");
        shutdownExecutorIfNotNull(ruleActionExecutor, "Rule Action");
    }

    private void shutdownExecutorIfNotNull(ExecutorService executor, String name) {
        if (executor != null) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
            log.info("{} 线程池已关闭", name);
        }
    }
}
