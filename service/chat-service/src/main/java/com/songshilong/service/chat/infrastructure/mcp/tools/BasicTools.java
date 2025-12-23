package com.songshilong.service.chat.infrastructure.mcp.tools;

import com.songshilong.service.chat.infrastructure.mcp.local.AgentTool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.agent.tools
 * @Author: Ice, Song
 * @CreateTime: 2025-12-09  16:52
 * @Description: BasicTools
 * @Version: 1.0
 */
@Component
public class BasicTools {

//    @AgentTool(name = "current_time", description = "获取当前的时间，格式为YYYY-MM-DD HH:MM:SS")
    public String currentTime() {
        return LocalDateTime.now().toString().replace('T', ' ');
    }

}
