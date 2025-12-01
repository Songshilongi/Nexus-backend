package com.songshilong.service.chat.infrastructure.llm.core;

import com.songshilong.service.chat.infrastructure.llm.core.req.ChatRequest;
import com.songshilong.service.chat.infrastructure.llm.message.Message;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-30  22:42
 * @Description: LLMInteraction (LLM 交互能力接口)
 * @Version: 1.0
 */
public interface LLMInteraction {
    /**
     * 调用LLM接口）
     * @param userQuestion 用户问题
     * @return LLM响应内容
     */
    String call(String userQuestion);

    /**
     * 调用LLM接口（多轮对话）
     * @param messages 消息列表
     * @return LLM响应内容
     */
    String call(List<Message> messages);
    /**
     * 调用LLM接口（使用ChatRequest）
     * @param chatRequest ChatRequest对象
     * @return LLM响应内容
     */
    String call(ChatRequest chatRequest);


}
