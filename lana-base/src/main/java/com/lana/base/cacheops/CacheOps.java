package com.lana.base.cacheops;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/** ops
 * @auther liuyulet
 * @date 2024/3/16 12:39
 */
@Component
public class CacheOps {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public final static long DEFAULT_EXPIRE = 86400L;

    public final static long NO_EXPIRE = -1L;
    /**
     * 设置键值对，并可选地设置过期时间。
     *
     * @param key 键
     * @param value 值
     * @param expire 过期时间，如果为NOT_EXPIRE，则表示不设置过期时间
     */
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != NO_EXPIRE) {
            expire(key, expire);
        }
    }

    /**
     * 设置键值对，不设置过期时间。
     *
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {

        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取键对应的值，并可选地设置过期时间。
     *
     * @param key 键
     * @param expire 过期时间，如果为NOT_EXPIRE，则表示不设置过期时间
     * @return 键对应的值
     */
    public Object get(String key, long expire) {
        Object value = redisTemplate.opsForValue().get(key);
        if (expire != NO_EXPIRE) {
            expire(key, expire);
        }
        return value;
    }

    /**
     * 获取键对应的值，不设置过期时间。
     *
     * @param key 键
     * @return 键对应的值
     */
    public Object get(String key) {

        return get(key, NO_EXPIRE);
    }

    /**
     * 对键的值递增。
     *
     * @param key 键
     * @return 递增后的值
     */
    public Long increment(String key) {

        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 检查键是否存在。
     *
     * @param key 键
     * @return 如果键存在返回true，否则返回false
     */
    public Boolean hasKey(String key) {

        return redisTemplate.hasKey(key);
    }

    /**
     * 获取匹配模式的键集合。
     *
     * @param pattern 键的模式
     * @return 匹配模式的键集合
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除键。
     *
     * @param key 要删除的键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个键。
     *
     * @param keys 要删除的键集合
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 获取哈希表中指定字段的值。
     *
     * @param key 哈希表的键
     * @param field 哈希表中的字段
     * @return 字段的值
     */
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取哈希表中的所有字段和值。
     *
     * @param key 哈希表的键
     * @return 哈希表中的所有字段和值的映射
     */
    public Map<String, Object> hGetAll(String key) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    /**
     * 向哈希表中批量设置字段和值。
     *
     * @param key 哈希表的键
     * @param map 字段和值的映射
     */
    public void hMSet(String key, Map<String, Object> map) {
        hMSet(key, map, DEFAULT_EXPIRE);
    }

    /**
     * 向哈希表中批量设置字段和值，并可选地设置过期时间。
     *
     * @param key 哈希表的键
     * @param map 字段和值的映射
     * @param expire 过期时间，如果为NOT_EXPIRE，则表示不设置过期时间
     */
    public void hMSet(String key, Map<String, Object> map, long expire) {
        redisTemplate.opsForHash().putAll(key, map);

        if (expire != NO_EXPIRE) {
            expire(key, expire);
        }
    }

    /**
     * 向哈希表中设置字段和值。
     *
     * @param key 哈希表的键
     * @param field 哈希表中的字段
     * @param value 字段的值
     */
    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, DEFAULT_EXPIRE);
    }

    /**
     * 向哈希表中设置字段和值，并可选地设置过期时间。
     *
     * @param key 哈希表的键
     * @param field 哈希表中的字段
     * @param value 字段的值
     * @param expire 过期时间，如果为NOT_EXPIRE，则表示不设置过期时间
     */
    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);

        if (expire != NO_EXPIRE) {
            expire(key, expire);
        }
    }

    /**
     * 设置键的过期时间。
     *
     * @param key 键
     * @param expire 过期时间，单位为秒
     */
    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置键的过期时间戳。
     *
     * @param key 键
     * @param expire 过期时间戳
     */
    public void expireAt(String key, Date expire) {
        redisTemplate.expireAt(key, expire);
    }

    /**
     * 获取键的过期时间，单位为秒。
     *
     * @param key 键
     * @return 键的过期时间，如果键没有过期时间，则返回-2，如果键不存在，则返回-1
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 将值插入列表的头部，并可选地设置过期时间。
     *
     * @param key 列表的键
     * @param value 要插入的值
     */
    public void leftPush(String key, Object value) {

        leftPush(key, value, DEFAULT_EXPIRE);
    }

    /**
     * 将值插入列表的头部，并可选地设置过期时间。
     *
     * @param key 列表的键
     * @param value 要插入的值
     * @param expire 过期时间，如果为NOT_EXPIRE，则表示不设置过期时间
     */
    public void leftPush(String key, Object value, long expire) {
        redisTemplate.opsForList().leftPush(key, value);
        if (expire != NO_EXPIRE) {
            expire(key, expire);
        }
    }
    /**
     * 从指定列表的右侧弹出一个元素。
     *
     * 使用RedisTemplate的opsForList方法获取列表操作对象，然后调用其rightPop方法，
     * 从指定的列表的右侧弹出一个元素。如果列表为空，则返回null。
     *
     * @param key 列表的键名。这个键名在Redis中唯一标识了这个列表。
     * @return 弹出的元素。如果列表为空，则返回null。
     */
    public Object rightPop(String key) {
        if (!hasKey(key)) {
            return null;
        }
        return redisTemplate.opsForList().rightPop(key);
    }

}
