package com.songshilong.service.chat.infrastructure.mcp.model;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:55
 * @Description: Content
 * @Version: 1.0
 */
public record Content(String type, String text) {
    public static Content text(String text) {
        return new Content("text", text);
    }
}
