package com.songshilong.starter.mail.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.mail.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  13:54
 * @Description: MailProperty
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = MailProperty.PREFIX)
public class MailProperty {
    public static final String PREFIX = "chemical.property.email";

    /**
     * 邮箱验证码有效时间
     */
    private String codeValidTime;
    /**
     * 有效时间单位
     */
    private TimeUnit timeUnit;
}
