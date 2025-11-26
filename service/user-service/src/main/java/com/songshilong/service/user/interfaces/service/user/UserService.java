package com.songshilong.service.user.interfaces.service.user;

import com.songshilong.service.user.domain.user.req.UserRegisterRequest;
import com.songshilong.service.user.domain.user.res.UserRegisterResponse;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.interfaces.service.user
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:36
 * @Description: UserService
 * @Version: 1.0
 */
public interface UserService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);
}
