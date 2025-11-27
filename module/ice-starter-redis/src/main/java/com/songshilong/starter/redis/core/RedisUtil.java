package com.songshilong.starter.redis.core;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;

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
    public Boolean setIsMember(String key, Object obj) {
        return redisTemplate.opsForSet().isMember(key, obj);
    }

    @Override
    public void setAdd(String key, String... value) {
        redisTemplate.opsForSet().add(key, value);
    }

}
