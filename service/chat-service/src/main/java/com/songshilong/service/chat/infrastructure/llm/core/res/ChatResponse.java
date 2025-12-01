package com.songshilong.service.chat.infrastructure.llm.core.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-01  10:06
 * @Description: ChatResponse
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {
    private String id;
    private List<Choice> choices;
    private Integer created;
    private String model;
    private String object;
    private String serviceTier;
    private String systemFingerprint;
    private Usage usage;
}
