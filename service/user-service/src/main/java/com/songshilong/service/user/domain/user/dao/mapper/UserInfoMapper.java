package com.songshilong.service.user.domain.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songshilong.service.user.domain.user.dao.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user.domain.user.dao.mapper
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:18
 * @Description: UserInfoMapper
 * @Version: 1.0
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {
}
