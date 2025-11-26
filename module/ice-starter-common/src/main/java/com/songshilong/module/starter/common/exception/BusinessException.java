package com.songshilong.module.starter.common.exception;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  20:50
 * @Description: BusinessException
 * @Version: 1.0
 */
public class BusinessException extends AbstractException{
    /**
     * Constructor
     *
     * @param message   异常信息
     * @param cause     异常原因
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     */
    public BusinessException(String message, Throwable cause, Integer errorCode, String errorMsg) {
        super(message, cause, errorCode, errorMsg);
    }

    public BusinessException(String message, Integer errorCode, String errorMsg) {
        this(message, null, errorCode, errorMsg);
    }

    public BusinessException(String message, Integer errorCode) {
        this(message, null, errorCode, null);
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        this(null, null, errorCode, errorMsg);
    }


    public BusinessException(ExceptionHandler handler) {
        this(handler.errorCode(), handler.errorMsg());
    }
}
