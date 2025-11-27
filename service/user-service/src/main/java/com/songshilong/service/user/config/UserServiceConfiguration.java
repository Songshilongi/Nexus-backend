package com.songshilong.service.user.config;

import com.songshilong.module.starter.common.properties.UserJwtProperty;
import com.songshilong.service.user.infrastructure.properties.UsernameBloomFilterProperty;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.config
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  11:15
 * @Description: UserServiceConfiguration
 * @Version: 1.0
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties({UsernameBloomFilterProperty.class, UserJwtProperty.class})
public class UserServiceConfiguration implements WebMvcConfigurer {

    @Bean
    public RBloomFilter<String> usernameBloomFilter(RedissonClient redissonClient, UsernameBloomFilterProperty usernameBloomFilterProperty) {
        RBloomFilter<String> usernameBloomFilter = redissonClient.getBloomFilter(usernameBloomFilterProperty.getName());
        usernameBloomFilter.tryInit(usernameBloomFilterProperty.getExpectedInterceptors(), usernameBloomFilterProperty.getFalseProbability());
        return usernameBloomFilter;
    }

    // 👇 添加 CORS 全局配置 （TODO 如果使用Nginx或者Gateway之后可以处理掉）
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")               // 所有路径
                .allowedOrigins("*")             // 允许所有源（⚠️不能与 allowCredentials(true) 同时使用）
                .allowedMethods("*")             // 允许所有 HTTP 方法
                .allowedHeaders("*")             // 允许所有请求头
                .allowCredentials(false);        // 必须为 false（因为 origins 是 *）
    }
}
