package com.lana.rules.executes.stream;

import com.lana.base.cacheops.RedisCacheOps;
import com.lana.base.cacheops.stream.RedisStreamConfigProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuyulet
 * @create 2025/3/6 15:49
 */
@Configuration
public class RedisConsumer {
    /**
     * 注入Redis缓存操作工具类。
     */
    @Resource
    private RedisCacheOps redisCacheOps;

    @Resource
    private ConsumerRuleOne consumerRuleOne;


    @Resource
    private ConsumerRuleTwo consumerRuleTwo;

    @Resource
    private ConsumerRuleJob consumerRuleJob;
    @Resource(name = "redisStreamTaskExecutor")
    private ExecutorService redisStreamExecutor;

    @Resource
    private RedisStreamConfigProperties redisStreamConfigProperties;

    /**
     * 配置并创建StreamMessageListenerContainer，用于监听和处理Redis流消息。
     *
     * @return 配置好的StreamMessageListenerContainer实例
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer() {


        // 配置StreamMessageListenerContainer的选项
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options =
                StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                        .builder()
                        .batchSize(redisStreamConfigProperties.getBatchSize())
                        .executor(runnable -> redisStreamExecutor.submit(runnable)) // 使用注入的线程池
                        .pollTimeout(Duration.ofSeconds(redisStreamConfigProperties.getPollTimeoutSeconds()))
                        //.errorHandler(new StreamErrorHandler()) // 可选异常处理
                        .build();

        // 创建StreamMessageListenerContainer实例
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer =
                StreamMessageListenerContainer.create(redisCacheOps.getRedisConnectionFactory(), options);


        // 注册消费者1到容器
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(redisStreamConfigProperties.getStreams().get(0).getGroup(), "consumerRuleOne"),
                StreamOffset.create(redisStreamConfigProperties.getStreams().get(0).getName(), ReadOffset.lastConsumed()), consumerRuleOne);

        // 注册消费者2到容器
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(redisStreamConfigProperties.getStreams().get(1).getGroup(), "consumerRuleTwo"),
                StreamOffset.create(redisStreamConfigProperties.getStreams().get(1).getName(), ReadOffset.lastConsumed()), consumerRuleTwo);
        // 注册消费者3到容器
        streamMessageListenerContainer.receiveAutoAck(Consumer.from(redisStreamConfigProperties.getStreams().get(2).getGroup(), "ConsumerRuleJob"),
                StreamOffset.create(redisStreamConfigProperties.getStreams().get(2).getName(), ReadOffset.lastConsumed()), consumerRuleJob);
        // 返回配置好的StreamMessageListenerContainer实例
        return streamMessageListenerContainer;
    }
}
