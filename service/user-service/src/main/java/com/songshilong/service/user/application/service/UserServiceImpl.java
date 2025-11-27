package com.songshilong.service.user.application.service;

import com.songshilong.module.starter.common.constants.RedisKeyConstant;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.ClientException;
import com.songshilong.module.starter.common.exception.enums.ClientExceptionEnum;
import com.songshilong.module.starter.common.exception.enums.UserExceptionEnum;
import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.module.starter.common.utils.Md5SecurityUtil;
import com.songshilong.service.user.domain.user.dao.entity.UserInfoEntity;
import com.songshilong.service.user.domain.user.dao.mapper.UserInfoMapper;
import com.songshilong.service.user.domain.user.req.UserRegisterRequest;
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

import java.util.Objects;

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
}
