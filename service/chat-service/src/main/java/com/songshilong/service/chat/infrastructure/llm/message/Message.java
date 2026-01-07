package com.songshilong.service.chat.infrastructure.llm.message;

import cn.hutool.core.collection.CollectionUtil;
import com.songshilong.service.chat.domain.chat.req.ChatCallRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
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
    @Field("content")
    private List<Content> content;

    public static Message ofUser(String content) {
        Content text = Content.ofText(content);
        return new Message(MessageRoleEnum.USER.getRole(), List.of(text));
    }

    public static Message ofUser(String content, List<String> urls) {
        List<Content> contents = new ArrayList<>();
        contents.add(Content.ofText(content));
        urls.forEach(url -> contents.add(Content.ofImage(url)));
        return new Message(MessageRoleEnum.USER.getRole(), contents);
    }

    public static Message ofUserToolUsed(ChatCallRequest chatCallRequest) {
        List<String> imageUrls = chatCallRequest.getImageUrls();
        if (CollectionUtil.isEmpty(imageUrls)) {
            return null;
        }
        String content = "我上传的在线图像链接如下: \n" + String.join("\n", imageUrls);
        return Message.ofUser(content);
    }

    public static Message ofAssistant(String content) {
        Content text = Content.ofText(content);
        return new Message(MessageRoleEnum.ASSISTANT.getRole(), List.of(text));
    }

    public static Message ofSystem(String content) {
        Content text = Content.ofText(content);
        return new Message(MessageRoleEnum.SYSTEM.getRole(), List.of(text));
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
