package com.songshilong.service.chat.interfaces.service.secrect;

import com.songshilong.service.chat.domain.secrect.req.CreateConfigurationRequest;
import com.songshilong.service.chat.domain.secrect.req.UpdateConfigurationRequest;
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

    /**
     * 创建配置
     * @param userId 用户ID
     * @param createConfigurationRequest 创建配置请求参数{@link CreateConfigurationRequest}
     * @return 是否创建成功
     */
    Boolean createConfiguration(Long userId, CreateConfigurationRequest createConfigurationRequest);

    /**
     * 更新配置
     * @param userId 用户ID
     * @param updateConfigurationRequest 更新配置请求参数{@link UpdateConfigurationRequest}
     * @return 是否更新成功
     */
    Boolean updateConfiguration(Long userId, UpdateConfigurationRequest updateConfigurationRequest);

    /**
     * 删除配置
     * @param userId 用户ID
     * @param configurationName 配置名字
     * @return 是否删除成功
     */
    Boolean deleteConfiguration(String userId, String configurationName);
}
