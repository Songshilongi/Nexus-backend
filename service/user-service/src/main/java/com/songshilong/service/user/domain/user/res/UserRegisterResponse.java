package com.songshilong.service.user.domain.user.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.domain.user.req
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:35
 * @Description: UserRegisterResponse
 * @Version: 1.0
 */
@Data
@ApiModel(description = "用户注册响应参数")
public class UserRegisterResponse {

    @ApiModelProperty("用户名/用户昵称")
    private String username;

    @ApiModelProperty("用户邮箱")
    private String email;
}
