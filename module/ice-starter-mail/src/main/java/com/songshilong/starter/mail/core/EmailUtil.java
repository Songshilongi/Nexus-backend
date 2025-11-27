package com.songshilong.starter.mail.core;

import cn.hutool.core.util.StrUtil;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.enums.MailExceptionEnum;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.mail.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  13:55
 * @Description: EmailUtil
 * @Version: 1.0
 */
@RequiredArgsConstructor
public class EmailUtil implements EmailService{
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sourceEmailAddress;

    @Override
    public Boolean send(String targetEmailAddress, String mailSubject, String mailContent) {
        if (StrUtil.isBlank(targetEmailAddress) || StrUtil.isBlank(mailContent)) {
            throw new BusinessException(MailExceptionEnum.UNKNOWN_TARGET);
        }
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sourceEmailAddress);
            mimeMessageHelper.setTo(targetEmailAddress);
            mimeMessageHelper.setSubject(mailSubject);
            mimeMessageHelper.setText(mailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new BusinessException(MailExceptionEnum.SEND_FAIL);
        }
        return Boolean.TRUE;
    }
}
