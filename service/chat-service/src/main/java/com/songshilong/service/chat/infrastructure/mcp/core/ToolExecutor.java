package com.songshilong.service.chat.infrastructure.mcp.core;

import com.songshilong.service.chat.infrastructure.mcp.model.McpTool;

import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  14:01
 * @Description: ToolExecutor - MCP 方法执行抽象接口
 * @Version: 1.0
 */
public interface ToolExecutor {

    /**
     * 获取工具定义
     *
     * @return {@link McpTool}
     */
    McpTool getDefinition();


    /**
     * 执行工具方法
     *
     * @param args 参数
     * @return 结果
     */
    Object execute(Map<String, Object> args);
}
