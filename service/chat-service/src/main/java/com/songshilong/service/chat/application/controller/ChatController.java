package com.songshilong.service.chat.application.controller;

import com.songshilong.module.starter.common.result.Result;
import com.songshilong.service.chat.domain.chat.req.AddMessageRequest;
import com.songshilong.service.chat.domain.chat.req.ChatCallRequest;
import com.songshilong.service.chat.domain.chat.res.ConversationHistoryResponse;
import com.songshilong.service.chat.domain.chat.vo.ConversationDetailView;
import com.songshilong.service.chat.interfaces.service.chat.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.List;

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


    @PostMapping(value = "/call/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ApiOperation("调用LLM接口-流式响应")
    public Flux<String> chatStream(@RequestBody @Validated ChatCallRequest chatCallRequest) {
        return chatService.chatStream(chatCallRequest);
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
    @ApiOperation("向指定会话记录添加消息")
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

    @PostMapping("conversation/{userId}/create")
    @ApiOperation("创建新的会话记录")
    public Result<Long> createConversation(
            @ApiParam("用户ID")
            @PathVariable("userId") Long userId
    ) {
        Long conversationId = chatService.createConversation(userId);
        return Result.success(conversationId);
    }

    @DeleteMapping("conversation/{userId}/{conversationId}")
    @ApiOperation("删除指定会话记录")
    public Result<Boolean> deleteConversation(
            @ApiParam("用户ID")
            @PathVariable("userId") Long userId,
            @ApiParam("对话ID")
            @PathVariable("conversationId") Long conversationId
    ) {
        Boolean res = chatService.deleteConversation(userId, conversationId);
        return Result.success(res);
    }


    @PostMapping("image/upload-batch")
    @ApiOperation("上传图片-批量")
    public Result<List<String>> uploadImage(@RequestPart("files") List<MultipartFile> files) {
        List<String> urls = chatService.uploadImageBatch(files);
        return Result.success(urls);
    }

    @PostMapping("image/upload-single")
    @ApiOperation("上传图片-单个")
    public Result<String> uploadImage(@RequestPart("file") MultipartFile file) {
        String url = chatService.uploadImage(file);
        return Result.success(url);
    }

}
