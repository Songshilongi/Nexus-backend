package com.songshilong.service.chat.config;

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
public class ChatServiceConfiguration implements WebMvcConfigurer {

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
