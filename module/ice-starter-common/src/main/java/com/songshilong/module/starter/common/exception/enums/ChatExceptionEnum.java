package com.songshilong.module.starter.common.exception.enums;

import com.songshilong.module.starter.common.exception.ExceptionHandler;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception.enums
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  21:28
 * @Description: ChatExceptionEnum
 * @Version: 1.0
 */
public enum ChatExceptionEnum implements ExceptionHandler {
    CONVERSATION_NOT_FOUND(60001, "对话未找到"),
    ADD_MESSAGE_FAIL(60002, "添加消息失败"),
    DELETE_CONVERSATION_FAIL(60003, "对话记录删除失败"),
    MULTIPLE_LLM_CONFIGURATION_FOUND(60004, "加载用户LLM配置失败，存在多个同名配置"),
    CONFIGURATION_NOT_FOUND(60005, "自定义配置未找到"),
    CREATE_CONVERSATION_FAIL(60006, "创建对话失败"),
    ;


    private final Integer errorCode;
    private final String errorMsg;

    private ChatExceptionEnum(Integer errorCode, String errorMsg) {
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
