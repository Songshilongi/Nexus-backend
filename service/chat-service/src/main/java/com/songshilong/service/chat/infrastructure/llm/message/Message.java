package com.songshilong.service.chat.infrastructure.llm.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.message
 * @Author: Ice, Song
 * @CreateTime: 2025-11-29  23:12
 * @Description: Message 构造类
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
public class Message {

    @Field("role")
    private String role;
    @Field("contents")
    private List<Content> contents;

    public static Message ofUser(String content) {
        Content text = Content.ofText(content);
        return new Message(MessageRoleEnum.USER.getRole(), List.of(text));
    }

    public static Message ofAssistant(String content) {
        Content text = Content.ofText(content);
        return new Message(MessageRoleEnum.ASSISTANT.getRole(), List.of(text));
    }

    public static Message of(String role, String content) {
        MessageRoleEnum roleEnum = MessageRoleEnum.of(role);
        if (roleEnum == null) {
            throw new IllegalArgumentException("Unsupported message role: " + role);
        }
        Content text = Content.ofText(content);
        return new Message(roleEnum.getRole(), List.of(text));
    }

}
