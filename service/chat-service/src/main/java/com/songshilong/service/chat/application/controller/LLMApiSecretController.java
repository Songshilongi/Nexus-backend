package com.songshilong.service.chat.application.controller;

import com.songshilong.module.starter.common.result.Result;
import com.songshilong.service.chat.domain.secrect.res.LLMApiSecretConfigurationResponse;
import com.songshilong.service.chat.interfaces.service.secrect.LLMApiSecretService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
@RequestMapping("/llm-sec-configuration")
@Api(tags = "密钥管理相关接口")
public class LLMApiSecretController {

    private final LLMApiSecretService llmApiSecretService;

    @GetMapping("/list/{userId}")
    @ApiOperation("查询某个用户当前的配置")
    public Result<List<LLMApiSecretConfigurationResponse>> queryConfiguration(@PathVariable("userId") Long userId) {
        List<LLMApiSecretConfigurationResponse> llmApiSecretConfigurationResponseList = llmApiSecretService.queryConfiguration(userId);
        return Result.success(llmApiSecretConfigurationResponseList);
    }


}
