package com.songshilong.service.chat.application.controller;

import com.songshilong.module.starter.common.result.Result;
import com.songshilong.service.chat.domain.mcp.req.McpResourceRequest;
import com.songshilong.service.chat.domain.mcp.res.McpResourceResponse;
import com.songshilong.service.chat.interfaces.service.mcp.McpManagerService;
import com.songshilong.starter.database.base.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.application.controller
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  16:20
 * @Description: McpController
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mcp-manager")
@Api(tags = "MCP 管理相关接口")
public class McpManagerController {

    private final McpManagerService mcpManagerService;

    @GetMapping("/{userId}/servers")
    @ApiOperation("查询某个用户的 MCP 资源列表")
    public Result<PageResult<McpResourceResponse>> queryByUserId(@PathVariable Long userId, McpResourceRequest request) {
        PageResult<McpResourceResponse> response = this.mcpManagerService.queryByUserId(userId, request);
        return Result.success(response);
    }


}
