package com.songshilong.module.starter.common.exception;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  20:49
 * @Description: AbstractException
 * @Version: 1.0
 */
public class AbstractException extends RuntimeException {

    protected final Integer errorCode;
    protected final String errorMsg;

    public AbstractException(String message, Throwable cause, Integer errorCode, String errorMsg) {
        super(message, cause);
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
