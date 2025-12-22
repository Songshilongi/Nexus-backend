package com.songshilong.service.chat.infrastructure.mcp;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.ServiceException;
import com.songshilong.module.starter.common.exception.enums.AgentExceptionEnum;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import com.songshilong.service.chat.infrastructure.mcp.local.LocalToolRegistry;
import com.songshilong.service.chat.infrastructure.mcp.remote.RemoteMcpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
@Slf4j
public class AgentToolManager {

    private final LocalToolRegistry localRegistry;
    private final RemoteMcpService remoteService;

    private final Map<String, ToolExecutor> allToolsCache = new ConcurrentHashMap<>();

    /**
     * 获取当前会话可用的所有工具（本地 + 用户配置）
     *
     * @param userMcpUrls 用户在前端配置的 MCP 服务器地址列表
     */
    public Map<String, ToolExecutor> getToolsForSession(List<String> userMcpUrls) {
        allToolsCache.putAll(this.localRegistry.getTools());

        if (CollectionUtil.isNotEmpty(userMcpUrls)) {
            userMcpUrls.forEach(url -> {
                Map<String, ToolExecutor> remoteTools = this.remoteService.fetchToolsFromUrl(url);
                remoteTools.forEach((name, executor) -> {
                    if (allToolsCache.containsKey(name)) {
                        log.warn("Tool name conflict for '{}'. Remote tool from '{}' will override local tool.", name, url);
                    } else {
                        allToolsCache.put(name, executor);
                    }
                });
            });
        }
        return allToolsCache;
    }


    public Object execute(String toolName, Map<String, Object> arguments) {
        if (StrUtil.isBlank(toolName)) {
            throw new BusinessException(AgentExceptionEnum.EMPTY_TOOL_NAME);
        }
        ToolExecutor toolExecutor = this.allToolsCache.get(toolName);
        if (toolExecutor == null) {
            throw new ServiceException(AgentExceptionEnum.TOOL_NOT_FOUND);
        }
        return toolExecutor.execute(arguments);
    }

}
