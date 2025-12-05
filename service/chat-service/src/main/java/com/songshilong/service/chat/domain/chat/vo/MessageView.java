package com.songshilong.service.chat.domain.chat.vo;

import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.vo
 * @Author: Ice, Song
 * @CreateTime: 2025-12-05  14:34
 * @Description: MessageView
 * @Version: 1.0
 */
@Data
public class MessageView {
    private String role;
    private String content;
}
