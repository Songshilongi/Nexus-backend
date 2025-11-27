package com.songshilong.service.user.interfaces.service.user;

import com.songshilong.service.user.domain.user.req.PasswordMailResetRequest;
import com.songshilong.service.user.domain.user.req.ResetPasswordRequest;
import com.songshilong.service.user.domain.user.req.UserLoginRequest;
import com.songshilong.service.user.domain.user.req.UserRegisterRequest;
import com.songshilong.service.user.domain.user.res.PasswordMailResetResponse;
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

    /**
     * 获取密码重置邮箱验证码
     *
     * @param passwordMailResetRequest {@link PasswordMailResetRequest} 获取邮箱验证码的请求参数
     * @return {@link PasswordMailResetResponse} 响应结果
     */
    PasswordMailResetResponse getPasswordMailResetCode(PasswordMailResetRequest passwordMailResetRequest);

    /**
     * 通过邮件验证码重置密码
     *
     * @param resetPasswordRequest {@link ResetPasswordRequest} 重置密码的请求参数
     * @return true-重置成功 false-重置失败
     */
    Boolean resetPasswordByEmailVerifyCode(ResetPasswordRequest resetPasswordRequest);
}
