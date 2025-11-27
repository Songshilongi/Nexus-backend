package com.songshilong.service.user.interfaces.service.user;

import com.songshilong.service.user.domain.user.req.UserLoginRequest;
import com.songshilong.service.user.domain.user.req.UserRegisterRequest;
import com.songshilong.service.user.domain.user.res.UserLoginResponse;
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

    /**
     * 判断用户名是否已经存在
     *
     * @param username 用户名
     * @return true-已经存在 false-不存在
     */
    Boolean hasUsername(String username);

    /**
     * 注册
     *
     * @param userRegisterRequest {@link UserRegisterRequest} 注册需要的用户数据
     * @return {@link UserRegisterResponse} 注册结果
     */
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    /**
     * 登录
     *
     * @param userLoginRequest {@link UserLoginRequest} user login request param
     * @return {@link UserLoginResponse} login response
     */
    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
