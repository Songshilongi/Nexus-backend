package com.songshilong.service.chat.infrastructure.llm.core;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-30  22:38
 * @Description: LLMConfig
 * @Version: 1.0
 */
public record LLMConfig (String baseUrl, String apiKey, String modelId, Float temperature) {

}
