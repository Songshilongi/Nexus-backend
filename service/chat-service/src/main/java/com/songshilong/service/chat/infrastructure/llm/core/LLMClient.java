package com.songshilong.service.chat.infrastructure.llm.core;

import com.songshilong.service.chat.infrastructure.llm.core.req.ChatRequest;
import com.songshilong.service.chat.infrastructure.llm.message.Content;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.service.chat.infrastructure.utils.HttpUtils;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core
 * @Author: Ice, Song
 * @CreateTime: 2025-11-29  23:45
 * @Description: LLMClient 负责和LLM API进行交互
 * @Version: 1.0
 */

@Getter
public class LLMClient implements LLMInteraction {


    private final LLMConfig llmConfig;
    private final HttpUtils httpUtils;

    private static final String MODEL = "model";
    private static final String MESSAGES = "messages";
    private static final String TEMPERATURE = "temperature";
    private static final String ROLE = "role";
    private static final String CONTENT = "content";
    private static final String TEXT = "text";

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

    @Override
    public Flux<String> callStream(List<Message> messages, boolean enable_image) {
        String baseUrl = llmConfig.baseUrl();
        Map<String, Object> body = this.buildRequestPayload(messages, enable_image);
        Map<String, String> headers = this.buildRequestHeaders();
        return httpUtils.postForStream(baseUrl, body, headers);
    }

    private Map<String, String> buildRequestHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, "Bearer " + llmConfig.apiKey());
        return headers;
    }

    private Map<String, Object> buildRequestPayload(List<Message> messages, boolean enable_image) {
        Map<String, Object> body = new HashMap<>();
        body.put(MODEL, llmConfig.modelId());
        body.put(TEMPERATURE, llmConfig.temperature());
        if (enable_image) {
            body.put(MESSAGES, messages);
        } else {
            List<Map<String, String>> data = new ArrayList<>();
            for (Message message : messages) {
                Map<String, String> map = new HashMap<>();
                List<Content> contents = message.getContents();
                contents.stream()
                        .filter(content -> content.getType().equals(TEXT))
                        .findFirst()
                        .ifPresent(text -> map.put(CONTENT, text.getText()));
                map.put(ROLE, message.getRole());
                data.add(map);
            }
            body.put(MESSAGES, data);
        }
        body.put("stream", true);
        return body;
    }

}
