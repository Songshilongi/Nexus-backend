package com.songshilong.service.chat.infrastructure.agent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.service.chat.infrastructure.llm.core.LLMClient;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.service.chat.infrastructure.mcp.AgentToolManager;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import com.songshilong.service.chat.infrastructure.mcp.model.McpTool;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.agent
 * @Author: Ice, Song
 * @CreateTime: 2025-12-23  14:38
 * @Description: AbstractReactAgent
 * @Version: 1.0
 */
@Slf4j
public abstract class AbstractReactAgent {

    protected final LLMClient llmClient;
    protected final AgentToolManager agentToolManager;
    protected final int maxIterations = 5;
    protected final ObjectMapper objectMapper = BeanUtil.getMapper();


    public AbstractReactAgent(LLMClient llmClient, AgentToolManager agentToolManager) {
        this.llmClient = llmClient;
        this.agentToolManager = agentToolManager;
    }


    public abstract Flux<String> run(List<Message> history, Map<String, ToolExecutor> availableTools);


    protected String buildSystemPrompt(Map<String, ToolExecutor> tools) {
        StringBuilder sb = new StringBuilder();
        sb.append("你是一个具备工具调用能力的专业助手。请通过以下步骤协助用户：\n\n");

        sb.append("1. **Analyze**: 思考用户需求。\n");
        sb.append("2. **Thought**: 思考当前步骤应该做什么。\n");
        sb.append("3. **Action**: 选择工具名称（必须精确匹配）。\n");
        sb.append("4. **Action Input**: 输入符合 Schema 的 JSON 字符串（不要使用 Markdown 代码块，直接单行 JSON）。\n");
        sb.append("5. **Wait**: 输出完 Action Input 后 **立即停止**，等待用户反馈 Observation。\n");
        sb.append("6. **Observation**: (这部分由用户反馈工具执行结果，请勿自己生成)。\n");
        sb.append("... (重复上述步骤)\n");
        sb.append("7. **Final Answer**: 根据 Observation 生成最终回复。\n\n");

        sb.append("### 注意事项：\n");
        sb.append("- 遇到错误时，请根据 Observation 中的错误信息修正参数并重试。\n");
        sb.append("- 如果不需要调用工具，直接输出 Final Answer。\n\n");

        sb.append("### 可用工具列表：\n");

        tools.values().forEach(executor -> {
            McpTool tool = executor.getDefinition();
            sb.append("- 工具名: ").append(tool.name()).append("\n");
            sb.append("  描述: ").append(tool.description()).append("\n");
            try {
                String schemaJson = objectMapper.writeValueAsString(tool.inputSchema());
                sb.append("  参数Schema: ").append(schemaJson).append("\n");
            } catch (Exception e) {
                sb.append("  参数Schema: {}\n");
            }
        });

        return sb.toString();
    }


}
