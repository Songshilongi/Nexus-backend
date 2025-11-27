package com.songshilong.starter.mail.config;

import com.songshilong.starter.mail.core.EmailUtil;
import com.songshilong.starter.mail.core.MailProperty;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.mail.config
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  13:53
 * @Description: EmailAutoConfiguration
 * @Version: 1.0
 */
@AutoConfiguration
@EnableConfigurationProperties(MailProperty.class)
public class EmailAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EmailUtil emailService(JavaMailSender javaMailSender) {
        return new EmailUtil(javaMailSender);
    }
}
