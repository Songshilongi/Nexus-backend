package com.songshilong.module.starter.common.exception;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  20:51
 * @Description: ExceptionHandler
 * @Version: 1.0
 */
public interface ExceptionHandler {
    Integer errorCode();

    String errorMsg();
}
