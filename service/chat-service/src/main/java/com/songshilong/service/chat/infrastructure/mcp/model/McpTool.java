package com.songshilong.service.chat.infrastructure.mcp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:54
 * @Description: McpTool
 * @Version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record McpTool(
        String name,
        String description,
        InputSchema inputSchema
) {}
