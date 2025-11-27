package com.songshilong.starter.mail.core;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.mail.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  13:55
 * @Description: EmailService
 * @Version: 1.0
 */
public interface EmailService {
    Boolean send(String targetEmailAddress, String mailSubject, String mailContent);
}
