package com.songshilong.module.starter.common.exception.enums;

import com.songshilong.module.starter.common.exception.ExceptionHandler;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception.enums
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  13:55
 * @Description: MailExceptionEnum
 * @Version: 1.0
 */
public enum MailExceptionEnum implements ExceptionHandler {
    UNKNOWN_TARGET(40001, "需要发送的内容或者目标邮件地址为空，请检查！"),
    SEND_FAIL(40002, "邮件发送失败"),
    TOO_FREQUENCY(40003, "邮件发送过于频繁"),
    VERIFY_CODE_EXPIRED(40004, "验证码不存在或者已经过期"),
    VERIFY_CODE_WRONG(40004, "验证码不正确");


    private final Integer errorCode;
    private final String errorMsg;

    private MailExceptionEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer errorCode() {
        return this.errorCode;
    }

    public String errorMsg() {
        return this.errorMsg;
    }
}
