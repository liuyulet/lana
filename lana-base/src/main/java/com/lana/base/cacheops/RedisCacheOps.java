package com.lana.base.cacheops;

import jakarta.annotation.Resource;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.StreamInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/** ops
 * @auther liuyulet
 * @date 2024/3/16 12:39
 */
@Component
public class RedisCacheOps {


/*    @Resource
    private RedissonClient redissonClient;*/


    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    // todo 长期进行状态，用到什么功能，这里补充什么方法,如果使用高级功能，则替换redissonClient使用


    /**
     * 获取键对应的值，不设置过期时间。
     *
     * @param key 键
     * @return 键对应的值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置键值对，
     *
     * @param key 键
     * @param value 值
     * @param expire 过期时间，如果为NOT_EXPIRE，则表示不设置过期时间
     */
    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value,expire, TimeUnit.SECONDS);
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
        redisTemplate.opsForList().leftPush(key, value);
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
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);


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
        return redisTemplate.opsForList().rightPop(key);
    }


    /**
     * 创建一个消费者组
     * @param streamName
     * @param groupName
     * @return
     */
    public String createGroup(String streamName, String groupName) {
        // 创建一个名为streamName的流，并指定一个名为groupName的消费者组
        return redisTemplate.opsForStream().createGroup(streamName, groupName);
    }

    /**
     * 销毁一个消费者组
     * @param streamName
     * @param groupName
     * @return
     */
    public Boolean destroyGroup(String streamName, String groupName) {
        return redisTemplate.opsForStream().destroyGroup(streamName, groupName);
    }

    public StreamInfo.XInfoGroups groups(String streamName) {
        // 创建一个名为streamName的流
        return redisTemplate.opsForStream().groups(streamName);
    }


    /**
     * 添加一个map到redisStream中
     * @param streamName
     * @param value
     * @return
     */
    public String addMap(String streamName, Map<String, Object> value) {
        //System.out.println("正在尝试写入 Redis Stream: {}"+streamName);
        try {
            String recordId = redisTemplate.opsForStream().add(streamName, value).getValue();
            if (recordId == null) {
                //System.out.println("写入 Redis Stream 失败，返回 recordId 为 null，streamName: "+streamName);
            }
            return recordId;
        } catch (Exception e) {
            //System.out.println("写入 Redis Stream："+streamName+" 异常，streamName: "+e);
            return null;
        }
    }

    /**
     * 添加一个Record到redisStream中
     * @param record
     * @return
     */
    public String addRecord(Record<String,Object> record) {
        return redisTemplate.opsForStream().add(record).getValue();
    }

    /**
     * 确认一个Record已经被消费
     * @param streamName
     * @param groupName
     * @param recordId
     * @return
     */
    public Long ack(String streamName, String groupName, String... recordId) {
        return redisTemplate.opsForStream().acknowledge(streamName, groupName, recordId);
    }

    /**
     * 删除一个Record
     * @param streamName
     * @param recordId
     * @return
     */
    public Long del(String streamName, String... recordId) {
        return redisTemplate.opsForStream().delete(streamName, recordId);

    }

    /**
     * 获取RedisConnectionFactory
     * @return
     */
    public RedisConnectionFactory getRedisConnectionFactory(){

        //System.out.println("RedisConnectionFactory class: " + redisTemplate.getConnectionFactory().getClass());
        return redisTemplate.getConnectionFactory();
    }


    /**
     * 判断一个stream是否存在
     * @param streamKey
     * @return
     */
    public boolean isStreamExists(String streamKey) {
        try {
            StreamInfo.XInfoGroups streamInfo = redisTemplate.opsForStream().groups(streamKey);
            return streamInfo != null;
        } catch (Exception e) {
            return false;
        }

    }

}
