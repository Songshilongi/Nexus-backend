package com.songshilong.service.chat;

import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.service.chat.infrastructure.mcp.AgentToolManager;
import com.songshilong.service.chat.infrastructure.mcp.core.ToolExecutor;
import com.songshilong.service.chat.infrastructure.mcp.local.AgentTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat
 * @Author: Ice, Song
 * @CreateTime: 2025-12-09  16:56
 * @Description: TestAgent
 * @Version: 1.0
 */
@SpringBootTest
public class TestAgent {

    @Autowired
    AgentToolManager agentToolManager;

    @Test
    public void testInit() {
        Map<String, ToolExecutor> toolsForSession = agentToolManager.getToolsForSession(Collections.emptyList());
        toolsForSession.forEach((name, executor) -> {
            System.out.println("Tool Name: " + name);
            System.out.println("Tool Definition: ");
            System.out.println(executor.getDefinition());
            Object execute = executor.execute(null);
            System.out.println("Tool Execution Result: " + execute);
        });
    }
}
