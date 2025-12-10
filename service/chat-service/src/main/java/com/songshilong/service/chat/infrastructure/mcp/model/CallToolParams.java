package com.songshilong.service.chat.infrastructure.mcp.model;

import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:55
 * @Description: CallToolParams
 * @Version: 1.0
 */
public record CallToolParams(String name, Map<String, Object> arguments) {
}
