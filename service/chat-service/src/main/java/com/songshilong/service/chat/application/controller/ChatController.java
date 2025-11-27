package com.songshilong.service.chat.application.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
