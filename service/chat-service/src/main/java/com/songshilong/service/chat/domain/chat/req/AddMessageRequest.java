package com.songshilong.service.chat.domain.chat.req;

import com.songshilong.service.chat.infrastructure.llm.message.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.req
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  20:58
 * @Description: AddMessageRequest
 * @Version: 1.0
 */
@Data
@ApiModel("添加消息请求参数")
public class AddMessageRequest {

    @ApiModelProperty("消息所属角色 - user / assistant / system")
    @NotEmpty
    private String role;

    @ApiModelProperty("消息内容")
    @NotEmpty
    private String content;


    public static Message toMessage(AddMessageRequest request) {
        return Message.of(request.getRole(), request.getContent());
    }
}
