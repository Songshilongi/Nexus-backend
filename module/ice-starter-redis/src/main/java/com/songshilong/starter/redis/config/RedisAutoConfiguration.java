package com.songshilong.starter.redis.config;

import com.songshilong.starter.redis.core.RedisUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.redis.config
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:58
 * @Description: RedisAutoConfiguration
 * @Version: 1.0
 */
@AutoConfiguration
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RedisUtil redisUtil(StringRedisTemplate stringRedisTemplate) {
        return new RedisUtil(stringRedisTemplate);
    }
}

