package com.songshilong.service.user.domain.user.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.domain.user.res
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  14:05
 * @Description: PasswordMailResetResponse
 * @Version: 1.0
 */
@Data
@ApiModel(description = "重置密码邮件响应结果")
public class PasswordMailResetResponse {

    @ApiModelProperty("唯一用户名")
    private String username;

    @ApiModelProperty("邮箱地址")
    private String email;

}
