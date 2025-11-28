package com.songshilong.module.starter.common.exception.enums;

import com.songshilong.module.starter.common.exception.ExceptionHandler;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception.enums
 * @Author: Ice, Song
 * @CreateTime: 2025-11-28  11:03
 * @Description: LLMApiSecConfigurationExceptionEnum
 * @Version: 1.0
 */
public enum LLMApiSecConfigurationExceptionEnum implements ExceptionHandler {
    CREATE_CONFIGURATION_FAIL(50001, "创建自定义配置失败"),
    CONFIGURATION_EXIST(50002, "自定义配置已经存在"),
    UPDATE_FAIL(50003, "自定义配置更新失败");

    private final Integer errorCode;
    private final String errorMsg;

    private LLMApiSecConfigurationExceptionEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public Integer errorCode() {
        return errorCode;
    }

    @Override
    public String errorMsg() {
        return errorMsg;
    }
}
