package com.songshilong.service.chat.infrastructure.llm.core.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core.res
 * @Author: Ice, Song
 * @CreateTime: 2025-12-01  16:26
 * @Description: Message
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String content;
    private String reasoningContent;
    private String refusal;
    private String role;
    private Object audio;
    private Object functionalCall;
    private List<ToolCall> toolCalls;


}
