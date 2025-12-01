package com.songshilong.service.chat.infrastructure.llm.core;

import com.songshilong.service.chat.infrastructure.utils.HttpUtils;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-01  09:51
 * @Description: LLMClientBuilder
 * @Version: 1.0
 */
@Component
@Getter
public class LLMClientFactory {

    private final HttpUtils httpUtils;

    public LLMClientFactory(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    /**
     * 构建LLMClient
     * @param llmConfig 用户自己的LLM配置
     * @return {@link LLMClient}
     */
    public LLMClient createClient(LLMConfig llmConfig) {
        return new LLMClient(llmConfig, httpUtils);
    }


}
