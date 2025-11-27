package com.songshilong.service.chat.application.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.songshilong.service.chat.domain.secrect.dao.entity.LLMApiSecretConfigurationEntity;
import com.songshilong.service.chat.domain.secrect.dao.mapper.LLMApiSecretConfigurationMapper;
import com.songshilong.service.chat.domain.secrect.res.LLMApiSecretConfigurationResponse;
import com.songshilong.service.chat.interfaces.service.secrect.LLMApiSecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.application.service
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  21:34
 * @Description: LLMApiSecretServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class LLMApiSecretServiceImpl implements LLMApiSecretService {

    private final LLMApiSecretConfigurationMapper llmApiSecretConfigurationMapper;

    @Override
    public List<LLMApiSecretConfigurationResponse> queryConfiguration(Long userId) {
        LambdaQueryWrapper<LLMApiSecretConfigurationEntity> queryWrapper = Wrappers.lambdaQuery(LLMApiSecretConfigurationEntity.class)
                .eq(LLMApiSecretConfigurationEntity::getUserId, userId);
        List<LLMApiSecretConfigurationEntity> llmApiSecretConfigurationEntities = this.llmApiSecretConfigurationMapper.selectList(queryWrapper);
        return llmApiSecretConfigurationEntities.stream()
                .map(LLMApiSecretConfigurationResponse::convertFormEntity)
                .collect(Collectors.toList());
    }
}
