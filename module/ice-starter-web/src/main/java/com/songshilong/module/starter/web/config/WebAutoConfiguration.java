package com.songshilong.module.starter.web.config;

import com.songshilong.module.starter.web.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.web.config
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:00
 * @Description: WebAutoConfiguration
 * @Version: 1.0
 */
@AutoConfiguration
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }


}
