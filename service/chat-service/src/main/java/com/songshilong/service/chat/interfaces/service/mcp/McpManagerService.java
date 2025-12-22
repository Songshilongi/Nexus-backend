package com.songshilong.service.chat.interfaces.service.mcp;

import com.songshilong.service.chat.domain.mcp.req.McpResourceRequest;
import com.songshilong.service.chat.domain.mcp.res.McpResourceResponse;
import com.songshilong.service.chat.domain.secrect.res.LLMApiSecretConfigurationResponse;
import com.songshilong.starter.database.base.PageResult;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.interfaces.service.mcp
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  16:21
 * @Description: McpService
 * @Version: 1.0
 */
public interface McpManagerService {

    /**
     * 根据用户 ID 查询 MCP 资源
     *
     * @param userId  用户 ID
     * @param request MCP 资源请求对象
     * @return MCP 资源响应对象
     */
    PageResult<McpResourceResponse> queryByUserId(Long userId, McpResourceRequest request);
}
