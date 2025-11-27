package com.songshilong.service.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.songshilong.service.user.domain.user.dao.entity.UserInfoEntity;
import com.songshilong.service.user.domain.user.dao.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.user
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  10:43
 * @Description: TestConnection
 * @Version: 1.0
 */
@SpringBootTest
public class TestConnection {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Test
    public void testMySQL() {
        LambdaQueryWrapper<UserInfoEntity> select = Wrappers.lambdaQuery(UserInfoEntity.class).select();
        System.out.println(userInfoMapper.selectList(select));

    }
}
