package com.songshilong.service.chat.infrastructure.llm.message;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.message
 * @Author: Ice, Song
 * @CreateTime: 2025-11-29  23:04
 * @Description: MessageRole(LLM API 支持的消息类型)
 * @Version: 1.0
 */
@Getter
public enum MessageRoleEnum {
    SYSTEM("system", "系统提示词"),
    USER("user", "用户提问的内容"),
    ASSISTANT("assistant", "LLM响应的内容");

    private final String role;
    private final String description;

    MessageRoleEnum(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public static MessageRoleEnum of(String role) {
        if (StrUtil.isBlank(role)) {
            return null;
        }
        for (MessageRoleEnum value : MessageRoleEnum.values()) {
            if (value.getRole().equals(role)) {
                return value;
            }
        }
        return null;
    }

}
