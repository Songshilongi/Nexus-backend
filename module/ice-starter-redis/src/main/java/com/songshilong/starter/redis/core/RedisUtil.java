package com.songshilong.starter.redis.core;

import com.songshilong.module.starter.common.utils.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.redis.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:59
 * @Description: RedisUtil
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class RedisUtil implements Cache {

    private final StringRedisTemplate redisTemplate;

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String value = redisTemplate.opsForValue().get(key);
        return BeanUtil.toObject(value, clazz);
    }


    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    @Override
    public Boolean setIsMember(String key, Object obj) {
        return redisTemplate.opsForSet().isMember(key, obj);
    }

    @Override
    public void setAdd(String key, String... value) {
        redisTemplate.opsForSet().add(key, value);
    }

}
