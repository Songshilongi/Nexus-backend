package com.songshilong.service.chat.infrastructure.llm.message;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.message
 * @Author: Ice, Song
 * @CreateTime: 2025-11-29  23:12
 * @Description: Message 构造类
 * @Version: 1.0
 */
public record Message(String role, String content) {

    public static Message ofUser(String content) {
        return messageBuilder(MessageRoleEnum.USER, content);
    }

    public static Message ofSystem(String content) {
        return messageBuilder(MessageRoleEnum.SYSTEM, content);
    }


    public static Message ofAssistant(String content) {
        return messageBuilder(MessageRoleEnum.ASSISTANT, content);
    }

    public static Message of(String role, String content) {
        MessageRoleEnum roleEnum = MessageRoleEnum.of(role);
        if (roleEnum == null) {
            throw new IllegalArgumentException("Unsupported message role: " + role);
        }
        return messageBuilder(roleEnum, content);
    }

    private static Message messageBuilder(MessageRoleEnum roleEnum, String content) {
        return new Message(roleEnum.getRole(), content);

    }
}
