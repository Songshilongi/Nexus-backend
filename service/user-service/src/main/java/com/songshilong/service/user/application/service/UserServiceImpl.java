package com.songshilong.service.user.application.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.songshilong.module.starter.common.constants.Constant;
import com.songshilong.module.starter.common.constants.RedisKeyConstant;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.ClientException;
import com.songshilong.module.starter.common.exception.enums.ClientExceptionEnum;
import com.songshilong.module.starter.common.exception.enums.UserExceptionEnum;
import com.songshilong.module.starter.common.properties.UserJwtProperty;
import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.module.starter.common.utils.JwtUtil;
import com.songshilong.module.starter.common.utils.Md5SecurityUtil;
import com.songshilong.service.user.domain.user.dao.entity.UserInfoEntity;
import com.songshilong.service.user.domain.user.dao.mapper.UserInfoMapper;
import com.songshilong.service.user.domain.user.req.UserLoginRequest;
import com.songshilong.service.user.domain.user.req.UserRegisterRequest;
import com.songshilong.service.user.domain.user.res.UserLoginResponse;
import com.songshilong.service.user.domain.user.res.UserRegisterResponse;
import com.songshilong.service.user.infrastructure.utils.UserUtil;
import com.songshilong.service.user.interfaces.service.user.UserService;
import com.songshilong.starter.redis.core.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.application.controller.service
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:36
 * @Description: UserServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserInfoMapper userInfoMapper;
    private final RedissonClient redissonClient;
    private final RBloomFilter<String> usernameBloomFilter;
    private final RedisUtil redisUtil;
    private final UserJwtProperty userJwtProperty;

    @Override
    public Boolean hasUsername(String username) {
        boolean contains = usernameBloomFilter.contains(username);
        if (contains) {
            return redisUtil.setIsMember(RedisKeyConstant.USER_REGISTER_USERNAME + UserUtil.hashShardingIndex(username), username);
        }
        return Boolean.FALSE;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        if (Objects.isNull(userRegisterRequest)) {
            return null;
        }
        String username = userRegisterRequest.getUsername();
        if (this.hasUsername(username)) {
            throw new BusinessException(UserExceptionEnum.USER_EXIST);
        }

        RLock lock = redissonClient.getLock(RedisKeyConstant.USER_REGISTER_USERNAME_LOCK_PREFIX + username);
        if (!lock.tryLock()) {
            throw new ClientException(ClientExceptionEnum.REGISTER_GET_LOCK_FAIL);
        }
        try {
            UserInfoEntity userInfoEntity = BeanUtil.convert(userRegisterRequest, UserInfoEntity.class);
            if (Objects.isNull(userInfoEntity)) {
                throw new BusinessException(UserExceptionEnum.USER_REGISTER_FAIL);
            }
            userInfoEntity.setPassword(Md5SecurityUtil.getMd5Value(userInfoEntity.getPassword()));
            try {
                int insert = userInfoMapper.insert(userInfoEntity);
                if (insert != 1) {
                    throw new BusinessException(UserExceptionEnum.USER_REGISTER_FAIL);
                }
            } catch (DuplicateKeyException exception) {
                throw new BusinessException(UserExceptionEnum.USER_EXIST);
            }
            usernameBloomFilter.add(username);
            redisUtil.setAdd(RedisKeyConstant.USER_REGISTER_USERNAME + UserUtil.hashShardingIndex(username), username);
        } finally {
            lock.unlock();
        }
        return BeanUtil.convert(userRegisterRequest, UserRegisterResponse.class);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            throw new BusinessException(UserExceptionEnum.USER_LOGIN_FAIL_NONE_PASSWORD);
        }
        String md5Password = Md5SecurityUtil.getMd5Value(password);
        LambdaQueryWrapper<UserInfoEntity> queryWrapper = Wrappers.lambdaQuery(UserInfoEntity.class)
                .eq(UserInfoEntity::getUsername, username)
                .eq(UserInfoEntity::getPassword, md5Password);
        UserLoginResponse userLoginResponse = Optional.ofNullable(userInfoMapper.selectOne(queryWrapper))
                .map(userInfo -> {
                    UserLoginResponse convert = BeanUtil.convert(userInfo, UserLoginResponse.class);
                    convert.setUserId(userInfo.getId());
                    return convert;
                })
                .orElseThrow(() -> new BusinessException(UserExceptionEnum.USER_LOGIN_FAIL));
        userLoginResponse.setToken(this.getUserToken(userLoginResponse));
        return userLoginResponse;
    }

    private String getUserToken(UserLoginResponse userLoginResponse) {
        Map<String, String> claims = new HashMap<>();
        claims.put(Constant.USER_ID, String.valueOf(userLoginResponse.getUserId()));
        claims.put(Constant.USERNAME, userLoginResponse.getUsername());
        claims.put(Constant.EMAIL, userLoginResponse.getEmail());
        return JwtUtil.generateToken(userJwtProperty.getSecretKey(), userJwtProperty.getTtlMillis(), claims);
    }
}
