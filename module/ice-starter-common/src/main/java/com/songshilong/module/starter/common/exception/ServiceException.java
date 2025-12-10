package com.songshilong.module.starter.common.exception;

public class ServiceException extends AbstractException {
    /**
     * Constructor
     *
     * @param message   异常信息
     * @param cause     异常原因
     * @param errorCode 错误码
     * @param errorMsg  错误信息
     */
    public ServiceException(String message, Throwable cause, Integer errorCode, String errorMsg) {
        super(message, cause, errorCode, errorMsg);
    }

    public ServiceException(String message, Integer errorCode, String errorMsg) {
        this(message, null, errorCode, errorMsg);
    }

    public ServiceException(String message, Integer errorCode) {
        this(message, null, errorCode, null);
    }

    public ServiceException(Integer errorCode, String errorMsg) {
        this(null, null, errorCode, errorMsg);
    }

    public ServiceException(ExceptionHandler handler) {
        this(handler.errorCode(), handler.errorMsg());
    }
}
