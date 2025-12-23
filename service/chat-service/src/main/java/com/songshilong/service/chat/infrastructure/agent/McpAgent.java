package com.songshilong.service.chat.infrastructure.agent;

import com.fasterxml.jackson.databind.JsonNode;
import com.songshilong.service.chat.infrastructure.llm.core.LLMClient;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.service.chat.infrastructure.mcp.AgentToolManager;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class McpAgent extends AbstractReactAgent {

    public McpAgent(LLMClient llmClient, AgentToolManager agentToolManager) {
        super(llmClient, agentToolManager);
    }

    private static final Pattern ACTION_PATTERN = Pattern.compile("(?i)Action:\\s*([^\n]+)");
    private static final Pattern ACTION_INPUT_PATTERN = Pattern.compile("(?is)Action\\s+Input:\\s*(\\{.*\\})");

    @Override
    public Flux<String> run(List<Message> history, Map<String, ToolExecutor> availableTools) {
        String systemPrompt = super.buildSystemPrompt(availableTools);
        history.addFirst(Message.ofSystem(systemPrompt));
        return this.executeLoop(history, 0);
    }

    private Flux<String> executeLoop(List<Message> messages, int iteration) {
        if (iteration > maxIterations) {
            return Flux.just(createErrorChunk("执行步骤过多，已停止。"));
        }
        StringBuilder contentBuffer = new StringBuilder();
        Flux<String> rawStream = llmClient.callStream(messages, false);

        Flux<String> primaryStream = rawStream.doOnNext(jsonChunk -> {
            String content = extractContentFromChunk(jsonChunk);
            if (content != null) {
                contentBuffer.append(content);
            }
        });

        return primaryStream.concatWith(Flux.defer(() -> {
            String fullResponse = contentBuffer.toString();
            if (fullResponse.isEmpty()) {
                return Flux.empty();
            }
            log.info("Agent Step {} Full Accumulation: \n{}", iteration, fullResponse);
            String validResponse = truncateResponse(fullResponse);
            String action = parseAction(validResponse);

            if (action != null) {
                messages.add(Message.ofAssistant(validResponse));

                String observation;
                try {
                    Map<String, Object> args = parseArgs(validResponse);
                    log.info("Executing Tool: {}, Args: {}", action, args);

                    Object result = agentToolManager.execute(action, args);
                    observation = "Observation: " + result;
                } catch (Exception e) {
                    log.error("Tool execution failed", e);
                    observation = "Observation: Error: " + e.getMessage();
                }
                messages.add(Message.ofUser(observation));
                return executeLoop(messages, iteration + 1);
            }
            return Flux.empty();
        }));
    }

    /**
     * 解析单个 JSON Chunk，提取 delta content
     */
    private String extractContentFromChunk(String jsonChunk) {
        if (jsonChunk == null || jsonChunk.trim().isEmpty() || jsonChunk.contains("[DONE]")) {
            return null;
        }
        try {
            JsonNode node = objectMapper.readTree(jsonChunk);
            // 兼容 OpenAI 格式: choices[0].delta.content
            if (node.has("choices") && node.get("choices").isArray() && !node.get("choices").isEmpty()) {
                JsonNode choice = node.get("choices").get(0);
                if (choice.has("delta") && choice.get("delta").has("content")) {
                    String content = choice.get("delta").get("content").asText();
                    return ("null".equals(content)) ? null : content;
                }
            }
        } catch (Exception e) {
            // 忽略非 JSON 行或解析错误，保证流不中断
        }
        return null;
    }

    private String truncateResponse(String response) {
        int obsIndex = response.indexOf("Observation:");
        if (obsIndex != -1) {
            return response.substring(0, obsIndex).trim();
        }
        return response;
    }

    private String parseAction(String text) {
        Matcher matcher = ACTION_PATTERN.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> parseArgs(String text) {
        Matcher matcher = ACTION_INPUT_PATTERN.matcher(text);
        String jsonStr = null;
        if (matcher.find()) {
            jsonStr = matcher.group(1).trim();
        } else {
            int idx = text.lastIndexOf("Action Input:");
            if (idx != -1) {
                jsonStr = text.substring(idx + "Action Input:".length()).trim();
            }
        }

        if (jsonStr != null) {
            jsonStr = jsonStr.replaceAll("```json", "").replaceAll("```", "").trim();
            try {
                int start = jsonStr.indexOf("{");
                int end = jsonStr.lastIndexOf("}");
                if (start != -1 && end != -1 && end >= start) {
                    jsonStr = jsonStr.substring(start, end + 1);
                    return objectMapper.readValue(jsonStr, Map.class);
                }
            } catch (Exception e) {
                throw new RuntimeException("Args parse failed: " + jsonStr);
            }
        }
        return Collections.emptyMap();
    }

    private String createErrorChunk(String message) {
        return String.format("{\"choices\":[{\"delta\":{\"content\":\"%s\"}}]}", message);
    }
}
