package com.songshilong.service.gateway.filter;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.gateway.filter
 * @Author: Ice, Song
 * @CreateTime: 2025-12-31  14:49
 * @Description: UserLoginFilterConfig
 * @Version: 1.0
 */
@Data
public class UserLoginFilterConfig {

    /**
     * 黑名单列表-需要被拦截的
     */
    private List<String> blackPathPre;

    /**
     * 白名单，需要直接被放行
     */
    private List<String> whitePathPre;

    /**
     * JWT 密钥
     */
    private String jwtSecret;
}
