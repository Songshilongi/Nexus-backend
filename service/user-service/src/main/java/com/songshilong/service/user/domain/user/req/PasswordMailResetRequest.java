package com.songshilong.service.user.domain.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.domain.user.req
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  14:05
 * @Description: PasswordMailResetRequest
 * @Version: 1.0
 */
@Data
@ApiModel(description = "重置密码邮件请求参数")
public class PasswordMailResetRequest {

    @ApiModelProperty("唯一用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("邮箱地址")
    @Email(message = "邮箱地址格式不正确")
    private String email;


}
