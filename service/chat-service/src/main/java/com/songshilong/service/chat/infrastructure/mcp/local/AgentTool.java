package com.songshilong.service.chat.infrastructure.mcp.local;

import java.lang.annotation.*;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.agent.anno
 * @Author: Ice, Song
 * @CreateTime: 2025-12-09  15:55
 * @Description: AgentTool
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface AgentTool {
    /**
     * tool name - 工具名称
     */
    String name() default "";

    /**
     * tool description - 工具功能描述
     */
    String description();
}
