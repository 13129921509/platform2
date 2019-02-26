package com.cloud.userserver.config;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@EnableCaching
public class RedisClient {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void setObjectOfString(String key,Object value,long timeout) throws InterruptedException {
        RedisSerializer<String> stringRedisSerializer = stringRedisTemplate.getStringSerializer();
        if(timeout != Long.valueOf(0)){
            stringRedisTemplate.opsForValue().set(key,String.valueOf(value),timeout,TimeUnit.MILLISECONDS);
            //Assert.assertEquals(String.valueOf(value),stringRedisTemplate.opsForValue().get(key));
        }
    }
    public void setObjectOfString(String key,Object value) throws InterruptedException {
        setObjectOfString(key,value,Long.valueOf(0));
    }

    public String getObjectOfString(String key) throws InterruptedException {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
