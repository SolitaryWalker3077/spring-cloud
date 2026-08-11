package com.user.demo;


import com.common.demo.utils.Redis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisClientTest {
    @Autowired
    private Redis redis;

    @Test
    void test(){
        redis.set("aa", "ff");
    }
}
