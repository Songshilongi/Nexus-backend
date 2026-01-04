package com.songshilong.service.user.config;

import com.songshilong.module.starter.common.properties.UserJwtProperty;
import com.songshilong.service.user.infrastructure.properties.UsernameBloomFilterProperty;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

}
