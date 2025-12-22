package com.songshilong.service.chat.domain.chat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.dto
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  17:27
 * @Description: ConversationHistoryDTO
 * @Version: 1.0
 */
@Data
@ApiModel("对话历史DTO")
public class ConversationHistoryView {
    @ApiModelProperty("对话ID")
    private Long conversationId;
    @ApiModelProperty("对话摘要")
    private String summary;
}
