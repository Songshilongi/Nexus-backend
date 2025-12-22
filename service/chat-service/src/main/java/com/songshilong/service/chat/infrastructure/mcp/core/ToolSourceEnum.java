package com.songshilong.service.chat.infrastructure.mcp.core;

import lombok.Getter;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.core
 * @Author: Ice, Song
 * @CreateTime: 2025-12-22  15:02
 * @Description: ToolSourceEnum
 * @Version: 1.0
 */
@Getter
public enum ToolSourceEnum {
    LOCAL("LOCAL", "本地硬编码工具"),
    REMOTE("REMOTE", "远程 MCP 服务工具"),
    ;


    private final String source;
    private final String desc;

    private ToolSourceEnum(String source, String desc) {
        this.source = source;
        this.desc = desc;
    }
}
