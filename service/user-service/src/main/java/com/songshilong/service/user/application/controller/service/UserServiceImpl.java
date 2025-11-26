package com.songshilong.service.user.application.controller.service;

import com.songshilong.service.user.domain.user.req.UserRegisterRequest;
import com.songshilong.service.user.domain.user.res.UserRegisterResponse;
import com.songshilong.service.user.interfaces.service.user.UserService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.application.controller.service
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:36
 * @Description: UserServiceImpl
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        return null;
    }
}
