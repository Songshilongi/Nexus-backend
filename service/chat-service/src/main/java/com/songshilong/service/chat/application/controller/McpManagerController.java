package com.songshilong.service.chat.application.controller;

import com.songshilong.module.starter.common.result.Result;
import com.songshilong.service.chat.domain.mcp.req.McpResourceRequest;
import com.songshilong.service.chat.domain.mcp.req.NewMcpResourceRequest;
import com.songshilong.service.chat.domain.mcp.res.McpResourceResponse;
import com.songshilong.service.chat.interfaces.service.mcp.McpManagerService;
import com.songshilong.starter.database.base.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}/resources/list")
    @ApiOperation("查询某个用户的 MCP 资源列表")
    public Result<PageResult<McpResourceResponse>> queryByUserId(@PathVariable Long userId, McpResourceRequest request) {
        PageResult<McpResourceResponse> response = this.mcpManagerService.queryByUserId(userId, request);
        return Result.success(response);
    }


    @PostMapping("/{userId}/resources")
    @ApiOperation("添加 MCP 资源")
    public Result<Boolean> addMcpResource(@PathVariable Long userId, @RequestBody @Validated NewMcpResourceRequest request) {
        Boolean result = this.mcpManagerService.addMcpResource(userId, request);
        return Result.success(result);
    }

    @PutMapping("/{userId}/resources")
    @ApiOperation("更新 MCP 资源")
    public Result<Boolean> updateMcpResource(
            @ApiParam(value = "用户 ID", required = true)
            @PathVariable Long userId,
            @RequestBody @Validated NewMcpResourceRequest request) {
        Boolean result = this.mcpManagerService.updateMcpResource(userId, request);
        return Result.success(result);
    }

    @DeleteMapping("/{userId}/resources/{resourceName}")
    @ApiOperation("删除 MCP 资源")
    public Result<Boolean> deleteMcpResource(
            @ApiParam(value = "用户 ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "MCP 资源名称", required = true)
            @PathVariable String resourceName) {
        Boolean result = this.mcpManagerService.deleteMcpResource(userId, resourceName);
        return Result.success(result);
    }


}
