package com.songshilong.service.chat.interfaces.service.secrect;

import com.songshilong.service.chat.domain.secrect.res.LLMApiSecretConfigurationResponse;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.interfaces.service.secrect
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  21:34
 * @Description: TODO
 * @Version: 1.0
 */
public interface LLMApiSecretService {
    /**
     * 查询用户现有的配置信息
     * @param userId 用户ID
     * @return {@link LLMApiSecretConfigurationResponse}
     */
    List<LLMApiSecretConfigurationResponse> queryConfiguration(Long userId);
}
