package com.songshilong.service.chat;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  15:28
 * @Description: TestConnection
 * @Version: 1.0
 */
@SpringBootTest
public class TestConnection {
    @Autowired
    SnowflakeGenerator snowflakeGenerator;

    @Test
    public void gen() {
        long id = snowflakeGenerator.next();
        System.out.println("Generated ID: " + id);
    }
}
