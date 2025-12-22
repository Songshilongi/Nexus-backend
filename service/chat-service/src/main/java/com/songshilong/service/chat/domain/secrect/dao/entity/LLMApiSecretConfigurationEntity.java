package com.songshilong.service.chat.domain.secrect.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.starter.database.base.BaseEntity;
import lombok.*;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.secrect.dao.entity
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  20:38
 * @Description: LLMApiSecretEntity
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@TableName("llm_api_secret")
public class LLMApiSecretConfigurationEntity extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 用户自定义 LLM 配置的名字
     */
    private String configurationName;

    /**
     * API 密钥
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


}
