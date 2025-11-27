package com.songshilong.service.user.domain.user.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.domain.user.req
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  13:22
 * @Description: UserLoginRequest
 * @Version: 1.0
 */
@Data
@ApiModel("用户登陆请求参数")
public class UserLoginRequest {

    @ApiModelProperty("用户名")
    @NotNull
    private String username;

    @ApiModelProperty("密码")
    @NotNull
    private String password;
}
