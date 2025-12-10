package com.songshilong.service.chat.infrastructure.mcp.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:54
 * @Description: InputSchema
 * @Version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record InputSchema(String type, Map<String, Object> properties, List<String> required) {
}
