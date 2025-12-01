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
 * @CreateTime: 2025-12-01  16:13
 * @Description: ChatStreamResponse
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatStreamChunkResponse {
    private String id;
    private List<ChunkChoice> choices;
    private Integer created;
    private String model;
    private String object;
    private String serviceTier;
    private String systemFingerprint;
    private Usage usage;
}
