package com.songshilong.service.chat.infrastructure.mcp;

import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import com.songshilong.service.chat.infrastructure.mcp.local.LocalToolRegistry;
import com.songshilong.service.chat.infrastructure.mcp.remote.RemoteMcpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  14:09
 * @Description: AgentToolManager
 * @Version: 1.0
 */
@Component
@RequiredArgsConstructor
public class AgentToolManager {

    private final LocalToolRegistry localRegistry;
    private final RemoteMcpService remoteService;

    /**
     * 获取当前会话可用的所有工具（本地 + 用户配置）
     *
     * @param userMcpUrls 用户在前端配置的 MCP 服务器地址列表
     */
    public Map<String, ToolExecutor> getToolsForSession(List<String> userMcpUrls) {
        Map<String, ToolExecutor> allTools = new HashMap<>(localRegistry.getTools());
        Optional.ofNullable(userMcpUrls)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(remoteService::fetchToolsFromUrl)
                .forEach(allTools::putAll);
        return allTools;
    }
}
