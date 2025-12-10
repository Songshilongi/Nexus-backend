package com.songshilong.service.chat.infrastructure.mcp.model;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:55
 * @Description: ListToolsResult
 * @Version: 1.0
 */
public record ListToolsResult(List<McpTool> tools) {
}
