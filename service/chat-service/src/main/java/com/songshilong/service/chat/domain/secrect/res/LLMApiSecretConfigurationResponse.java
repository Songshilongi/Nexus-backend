package com.songshilong.service.chat.domain.secrect.res;

import com.songshilong.service.chat.domain.secrect.dao.entity.LLMApiSecretConfigurationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.secrect.res
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  21:43
 * @Description: LLMApiSecretConfigurationResponse
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LLMApiSecretConfigurationResponse {

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

    /**
     * 创建或者修改时间
     */
    private LocalDateTime createOrUpdateTime;

    public static LLMApiSecretConfigurationResponse convertFormEntity(LLMApiSecretConfigurationEntity llmApiSecretConfigurationEntity) {
        return LLMApiSecretConfigurationResponse.builder()
                .configurationName(llmApiSecretConfigurationEntity.getConfigurationName())
                .apiKey(llmApiSecretConfigurationEntity.getApiKey())
                .baseUrl(llmApiSecretConfigurationEntity.getBaseUrl())
                .llmModelId(llmApiSecretConfigurationEntity.getLlmModelId())
                .temperature(llmApiSecretConfigurationEntity.getTemperature())
                .createOrUpdateTime(llmApiSecretConfigurationEntity.getUpdateTime())
                .build();
    }

}
