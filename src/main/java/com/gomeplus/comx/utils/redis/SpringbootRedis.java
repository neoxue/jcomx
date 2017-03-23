package com.gomeplus.comx.utils.redis;

import com.gomeplus.comx.utils.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by xue on 3/22/17.
 */
public class SpringbootRedis extends AbstractCache{

    @Autowired
    static private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value, Integer ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
