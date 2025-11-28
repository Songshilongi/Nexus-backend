package com.songshilong.service.chat.application.controller;

import com.mysql.cj.QueryResult;
import com.songshilong.module.starter.common.result.Result;
import com.songshilong.service.chat.domain.secrect.req.CreateConfigurationRequest;
import com.songshilong.service.chat.domain.secrect.req.QueryConfigurationRequest;
import com.songshilong.service.chat.domain.secrect.req.UpdateConfigurationRequest;
import com.songshilong.service.chat.domain.secrect.res.LLMApiSecretConfigurationResponse;
import com.songshilong.service.chat.interfaces.service.secrect.LLMApiSecretService;
import com.songshilong.starter.database.base.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.application.controller
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  20:31
 * @Description: LLMApiSecretController
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/llm-configuration")
@Api(tags = "密钥管理相关接口")
public class LLMApiSecretController {

    private final LLMApiSecretService llmApiSecretService;

    @GetMapping("/users/{userId}")
    @ApiOperation("查询某个用户当前的配置")
    public Result<PageResult<LLMApiSecretConfigurationResponse>> queryConfiguration(
            @ApiParam(value = "用户ID", required = true) @PathVariable("userId") Long userId,
            @RequestBody QueryConfigurationRequest queryConfigurationRequest
    ) {
        PageResult<LLMApiSecretConfigurationResponse> llmApiSecretConfigurationResponseList = llmApiSecretService.queryConfiguration(userId, queryConfigurationRequest);
        return Result.success(llmApiSecretConfigurationResponseList);
    }

    @PostMapping("/users/{userId}")
    @ApiOperation("创建用户LLM配置")
    public Result<Boolean> createConfiguration(
            @ApiParam(value = "用户ID", required = true) @PathVariable("userId") Long userId,
            @RequestBody @Validated CreateConfigurationRequest createConfigurationRequest) {
        Boolean res = llmApiSecretService.createConfiguration(userId, createConfigurationRequest);
        return Result.success(res);
    }

    @PutMapping("/users/{userId}")
    @ApiOperation("更新某个配置")
    public Result<Boolean> updateConfiguration(
            @ApiParam(value = "用户ID", required = true) @PathVariable("userId") Long userId,
            @RequestBody @Validated UpdateConfigurationRequest updateConfigurationRequest) {
        Boolean res = llmApiSecretService.updateConfiguration(userId, updateConfigurationRequest);
        return Result.success(res);
    }

    @DeleteMapping("/users/{userId}/{configurationName}")
    @ApiOperation("删除某个用户配置")
    public Result<Boolean> deleteConfiguration(
            @ApiParam(value = "用户ID", required = true) @PathVariable("userId") String userId,
            @PathVariable("configurationName") String configurationName) {
        Boolean res = llmApiSecretService.deleteConfiguration(userId, configurationName);
        return Result.success(res);
    }


}
