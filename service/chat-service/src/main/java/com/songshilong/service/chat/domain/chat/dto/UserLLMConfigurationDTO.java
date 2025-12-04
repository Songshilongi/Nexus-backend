package com.songshilong.service.chat.domain.chat.dto;

import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.service.chat.domain.secrect.dao.entity.LLMApiSecretConfigurationEntity;
import com.songshilong.service.chat.infrastructure.llm.core.LLMConfig;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.dto
 * @Author: Ice, Song
 * @CreateTime: 2025-12-04  14:03
 * @Description: UserLLMConfigurationDTO
 * @Version: 1.0
 */
@Data
public class UserLLMConfigurationDTO {

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户自定义LLM配置的名字
     */
    private String configurationName;

    /**
     * API密钥
     */
    private String apiKey;

    /**
     * 用户自定义LLM配置的base_url
     */
    private String baseUrl;

    /**
     * 用户自定义LLM配置所使用的模型名字（例如qwen）
     */
    private String llmModelId;

    /**
     * LLM的temperature
     */
    private Float temperature;

    public static UserLLMConfigurationDTO fromEntity(LLMApiSecretConfigurationEntity entity) {
        if (entity == null) {
            return null;
        }
        return BeanUtil.convert(entity, UserLLMConfigurationDTO.class);
    }

    public static LLMConfig toLLMConfig(UserLLMConfigurationDTO dto) {
        if (dto == null) {
            return null;
        }
        return new LLMConfig(dto.getBaseUrl(), dto.getApiKey(), dto.getLlmModelId(), dto.getTemperature());
    }
}
