package com.songshilong.service.user.domain.user.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.starter.database.base.BaseEntity;
import lombok.*;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.domain.user.dao.entity
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:08
 * @Description: UserInfo
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user_info")
public class UserInfoEntity extends BaseEntity {
    /**
     * 主键 ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名/用户昵称
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

}
