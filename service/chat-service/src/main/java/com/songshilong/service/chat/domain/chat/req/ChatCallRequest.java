package com.songshilong.service.chat.domain.chat.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.req
 * @Author: Ice, Song
 * @CreateTime: 2025-12-04  13:55
 * @Description: ChatRequest
 * @Version: 1.0
 */
@Data
@ApiModel("聊天请求")
public class ChatCallRequest {

    @NotNull
    @ApiModelProperty("用户ID")
    private Long userId;

    @NotEmpty
    @ApiModelProperty("激活的LLM配置名称")
    private String configurationName;


    @NotNull
    @ApiModelProperty("会话ID")
    private Long conversationId;

    @NotEmpty
    @ApiModelProperty("用户提问内容")
    private String userQuestion;


}
