package com.songshilong.service.chat.application.controller;

import com.songshilong.module.starter.common.result.Result;
import com.songshilong.service.chat.domain.chat.req.AddMessageRequest;
import com.songshilong.service.chat.domain.chat.res.ConversationHistoryResponse;
import com.songshilong.service.chat.domain.chat.vo.ConversationDetailView;
import com.songshilong.service.chat.interfaces.service.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.application.controller
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  20:33
 * @Description: ChatController
 * @Version: 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Api(tags = "对话相关接口")
public class ChatController {

    private final ChatService chatService;

    /**
     * (要求用户在输入框输入内容后，调用此接口开始对话)点击空白对话框不算
     */
    @PostMapping("conversation/{userId}/start")
    @ApiOperation("开始对话")
    public Result<Void> startConversation(@PathVariable("userId") Long userId) {
        // TODO
        return Result.success(null);
    }

    @GetMapping("conversation/{userId}/history")
    @ApiOperation("查询所属用户的历史对话记录")
    public Result<ConversationHistoryResponse> queryConversationHistory(@PathVariable("userId") Long userId) {
        ConversationHistoryResponse historyResponse = chatService.queryConversationHistory(userId);
        return Result.success(historyResponse);
    }

    @GetMapping("conversation/{userId}/detail/{conversationId}")
    @ApiOperation("查询指定会话记录详情")
    public Result<ConversationDetailView> queryConversationDetail(
            @ApiParam("用户ID")
            @PathVariable("userId") Long userId,
            @ApiParam("对话ID")
            @PathVariable("conversationId") Long conversationId) {
        ConversationDetailView view = chatService.queryConversationDetail(userId, conversationId);
        return Result.success(view);
    }

    @PutMapping("conversation/{userId}/{conversationId}/message/add")
    public Result<Boolean> addMessageToConversation(
            @ApiParam("用户ID")
            @PathVariable("userId") Long userId,
            @ApiParam("对话ID")
            @PathVariable("conversationId") Long conversationId,
            @RequestBody @Validated AddMessageRequest addMessageRequest
    ) {
        Boolean res = chatService.addMessageToConversation(userId, conversationId, addMessageRequest);
        return Result.success(res);
    }

}
