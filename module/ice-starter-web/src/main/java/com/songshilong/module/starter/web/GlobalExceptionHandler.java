package com.songshilong.module.starter.web;

import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.web
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  20:58
 * @Description: GlobalExceptionHandler
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        return Result.fail(500, e.getMessage(), null);
    }

    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleBusinessException(BusinessException e) {
        return Result.fail(500, e.errorMsg(), null);
    }
    
}
