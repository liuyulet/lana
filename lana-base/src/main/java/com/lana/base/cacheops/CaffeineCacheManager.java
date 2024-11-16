package com.lana.base.cacheops;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.googlecode.aviator.Expression;
import org.springframework.stereotype.Component;

/**
 * @author Liuyulet
 * @version 1.0
 * @data 2024/9/12 14:59
 */
@Component
public class CaffeineCacheManager<K, V> {
    private static final ConcurrentMap<String, Cache<String, Expression>> cacheMap = new ConcurrentHashMap<>();


    // 创建一个缓存实例
    public static Cache<String, Expression> createCache(String cacheName, long expireAfterWrite, TimeUnit timeUnit, long maximumSize) {
        Caffeine<Object, Object> builder = Caffeine.newBuilder()
                .maximumSize(maximumSize);

        if (expireAfterWrite > 0) {
            builder.expireAfterWrite(expireAfterWrite, timeUnit);
        }

        Cache<String, Expression> cache = builder.build();
        cacheMap.put(cacheName, cache);
        return cache;
    }

    // 获取缓存实例
    //@SuppressWarnings("unchecked")
    public static Cache<String, Expression> getCache(String cacheName) {
        return (Cache<String, Expression>) cacheMap.get(cacheName);
    }

    // 设置值
    public static  void set(String cacheName, String key, Expression value, long expireAfterWrite, TimeUnit timeUnit) {
        Cache<String, Expression> cache = getCache(cacheName);
        if (cache == null) {
            cache = createCache(cacheName, expireAfterWrite, timeUnit, 150); // Default maximum size
        }
        cache.put(key, value);
    }

    // 获取值
    public static Expression get(String cacheName, String key) {
        Cache<String, Expression> cache = getCache(cacheName);
        return cache != null ? cache.getIfPresent(key) : null;
    }

    // 设置缓存过期时间 (全局设置)
    public static void expire(String cacheName, long expireAfterWrite, TimeUnit timeUnit) {
        Cache<String, Expression> cache = createCache(cacheName, expireAfterWrite, timeUnit, 150); // Default maximum size
        cacheMap.put(cacheName, cache);
    }
}