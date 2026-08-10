package com.user.demo.config;


import com.common.demo.utils.Redis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnProperty(prefix = "spring.data.redis",name = "host")
    public Redis redis(StringRedisTemplate redisTemplate) {
        return new Redis(redisTemplate);
    }
}