package com.songshilong.service.chat.infrastructure.llm.core;

import com.songshilong.service.chat.infrastructure.llm.core.req.ChatRequest;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.service.chat.infrastructure.utils.HttpUtils;
import lombok.Getter;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-29  23:45
 * @Description: LLMClient 负责和LLM API进行交互
 * @Version: 1.0
 */

@Getter
public class LLMClient implements LLMInteraction{


    private final LLMConfig llmConfig;
    private final HttpUtils httpUtils;

    public LLMClient(LLMConfig llmConfig, HttpUtils httpUtils) {
        this.llmConfig = llmConfig;
        this.httpUtils = httpUtils;
    }


    // TODO 实现LLMInteraction接口的方法，与具体的LLM API进行交互

    @Override
    public String call(String userQuestion) {
        return "";
    }

    @Override
    public String call(List<Message> messages) {
        return "";
    }

    @Override
    public String call(ChatRequest chatRequest) {
        return "";
    }
}
