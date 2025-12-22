package com.songshilong.service.chat.infrastructure.mcp.remote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songshilong.module.starter.common.exception.ServiceException;
import com.songshilong.module.starter.common.exception.enums.AgentExceptionEnum;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolSourceEnum;
import com.songshilong.service.chat.infrastructure.mcp.model.CallToolParams;
import com.songshilong.service.chat.infrastructure.mcp.model.JsonRpcMessage;
import com.songshilong.service.chat.infrastructure.mcp.model.ListToolsResult;
import com.songshilong.service.chat.infrastructure.mcp.model.McpTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.remote
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  14:08
 * @Description: RemoteMcpService
 * @Version: 1.0
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class RemoteMcpService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    /**
     * 根据 MCP Server URL 动态加载其下的所有工具
     */
    public Map<String, ToolExecutor> fetchToolsFromUrl(String mcpEndpointUrl) {
        Map<String, ToolExecutor> tools = new HashMap<>();
        try {

            JsonRpcMessage req = JsonRpcMessage.call("tools/list", null);
            JsonRpcMessage resp = restTemplate.postForObject(mcpEndpointUrl, req, JsonRpcMessage.class);

            if (resp != null && resp.result() != null) {
                ListToolsResult result = objectMapper.convertValue(resp.result(), ListToolsResult.class);

                for (McpTool toolDef : result.tools()) {
                    tools.put(toolDef.name(), new RemoteHttpExecutor(mcpEndpointUrl, toolDef, restTemplate, objectMapper));
                }
            }
        } catch (Exception e) {
            log.error("Failed to load MCP tools from {}", mcpEndpointUrl, e);
        }
        return tools;
    }

    private record RemoteHttpExecutor(String endpoint, McpTool definition, RestTemplate restTemplate,
                                      ObjectMapper mapper) implements ToolExecutor {
        @Override
        public McpTool getDefinition() {
            return definition;
        }

        @Override
        public Object execute(Map<String, Object> args) {
            try {
                CallToolParams params = new CallToolParams(definition.name(), args);
                JsonRpcMessage req = JsonRpcMessage.call("tools/call", params);

                JsonRpcMessage resp = restTemplate.postForObject(endpoint, req, JsonRpcMessage.class);

                if (resp != null && resp.error() != null) {
                    throw new ServiceException(AgentExceptionEnum.MCP_SERVER_ERROR);
                }
                return resp != null ? resp.result() : null;
            } catch (Exception e) {
                throw new ServiceException(AgentExceptionEnum.REMOTE_TOOL_EXECUTE_FAIL);
            }
        }

        @Override
        public String getSourceType() {
            return ToolSourceEnum.REMOTE.getSource();
        }
    }
}
