package com.songshilong.service.chat.infrastructure.llm.core.req;

import com.songshilong.service.chat.infrastructure.llm.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-01  09:59
 * @Description: ChatRequest
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private Boolean stream;
    private Float temperature;
}
