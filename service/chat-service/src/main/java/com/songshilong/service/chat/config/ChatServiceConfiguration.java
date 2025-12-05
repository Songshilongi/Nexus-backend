package com.songshilong.service.chat.config;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.songshilong.module.starter.common.properties.SnowFlakeProperty;
import com.songshilong.module.starter.common.utils.oss.AliOssUtil;
import com.songshilong.module.starter.common.utils.oss.AliYunOssProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.config
 * @Author: Ice, Song
 * @CreateTime: 2025-11-28  14:07
 * @Description: ChatServiceConfiguration
 * @Version: 1.0
 */
@Configuration
@EnableConfigurationProperties({SnowFlakeProperty.class, AliYunOssProperty.class})
@RequiredArgsConstructor
public class ChatServiceConfiguration implements WebMvcConfigurer {

    private final SnowFlakeProperty snowFlakeProperty;
    private final AliYunOssProperty aliYunOssProperty;

    @Bean
    @ConditionalOnMissingBean
    public SnowflakeGenerator snowflakeGenerator() {
        return new SnowflakeGenerator(snowFlakeProperty.getWorkerId(), snowFlakeProperty.getDataCenterId());
    }

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliYunOssProperty aliYunOssProperty) {
        return new AliOssUtil(aliYunOssProperty);
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
