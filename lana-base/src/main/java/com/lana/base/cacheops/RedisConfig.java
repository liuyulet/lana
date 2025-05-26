package com.lana.base.cacheops;

import com.lana.base.syshandle.exception.LanaException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @auther liuyulet
 * @date 2024/3/16 13:01
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        try {
            return setSerializer(factory,template);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LanaException("Failed to configure RedisTemplate", e);
        }
    }


    private RedisTemplate setSerializer(RedisConnectionFactory factory, RedisTemplate template) {

        // 设置键的序列化器
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());

        // 设置值的序列化器
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());

        // 设置 Redis 连接工厂
        template.setConnectionFactory(factory);
        return template;
    }

}
