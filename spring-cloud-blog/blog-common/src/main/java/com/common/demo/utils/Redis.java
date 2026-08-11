package com.common.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Redis {

    private StringRedisTemplate redisTemplate;

    private static final String REDIS_SPLIT = ":";
    private static final String REDIS_DEFAULT_PREFIX = "default";


    public Redis(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 获取指定的键值对
     * redis get
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("redis get error , key:{}", key);
            return null;
        }
    }

    /**
     * hasKey
     * true-存在 false 不存在
     */
    public boolean hasKey(String key) {
        try {
            return key == null ? null : redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("redis haskey error, key:{}", key);
            return false;
        }
    }


    /**
     * set key
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis set error, key:{}, value:{}, e:{}", key, value, e);
            return false;
        }
    }

    /**
     * set key, 设置过期时间, 单位为秒
     */
    public boolean set(String key, String value, long timeout) {
        try {
            if (timeout > 0) {
                redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            }else {
                set(key, value);
            }
            return true;
        }catch (Exception e) {
            log.error("redis set error, key:{}, value:{}", key, value);
            return false;
        }
    }


    public String buildKey(String prefix,String... args) {
        //1 zhangsan    user:1:zhangsan
        if (prefix==null){
            prefix = REDIS_DEFAULT_PREFIX;
        }
        StringBuilder key = new StringBuilder();
        key.append(prefix);
        if (args!=null){
            for (String arg: args){
                key.append(REDIS_SPLIT).append(arg);
            }
        }
        return key.toString();
    }
}
