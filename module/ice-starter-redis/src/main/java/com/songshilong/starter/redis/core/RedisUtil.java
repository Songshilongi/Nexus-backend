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
public class RedisUtil implements Cache{

    private final StringRedisTemplate stringRedisTemplate;


}
