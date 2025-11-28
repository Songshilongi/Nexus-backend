package com.songshilong.service.chat.application.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.enums.LLMApiSecConfigurationExceptionEnum;
import com.songshilong.module.starter.common.utils.BeanUtil;
import com.songshilong.service.chat.domain.secrect.dao.entity.LLMApiSecretConfigurationEntity;
import com.songshilong.service.chat.domain.secrect.dao.mapper.LLMApiSecretConfigurationMapper;
import com.songshilong.service.chat.domain.secrect.req.CreateConfigurationRequest;
import com.songshilong.service.chat.domain.secrect.req.UpdateConfigurationRequest;
import com.songshilong.service.chat.domain.secrect.res.LLMApiSecretConfigurationResponse;
import com.songshilong.service.chat.interfaces.service.secrect.LLMApiSecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
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

    @Override
    public Boolean createConfiguration(Long userId, CreateConfigurationRequest createConfigurationRequest) {
        LLMApiSecretConfigurationEntity entity = BeanUtil.convert(createConfigurationRequest, LLMApiSecretConfigurationEntity.class);
        entity.setUserId(userId);
        try {
            int insert = this.llmApiSecretConfigurationMapper.insert(entity);
            if (insert != 1) {
                throw new BusinessException(LLMApiSecConfigurationExceptionEnum.CREATE_CONFIGURATION_FAIL);
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(LLMApiSecConfigurationExceptionEnum.CONFIGURATION_EXIST);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateConfiguration(Long userId, UpdateConfigurationRequest updateConfigurationRequest) {
        LambdaUpdateWrapper<LLMApiSecretConfigurationEntity> updateWrapper = Wrappers.lambdaUpdate(LLMApiSecretConfigurationEntity.class)
                .set(LLMApiSecretConfigurationEntity::getApiKey, updateConfigurationRequest.getApiKey())
                .set(LLMApiSecretConfigurationEntity::getBaseUrl, updateConfigurationRequest.getBaseUrl())
                .set(LLMApiSecretConfigurationEntity::getLlmModelId, updateConfigurationRequest.getLlmModelId())
                .set(LLMApiSecretConfigurationEntity::getTemperature, updateConfigurationRequest.getTemperature())
                .eq(LLMApiSecretConfigurationEntity::getUserId, userId)
                .eq(LLMApiSecretConfigurationEntity::getConfigurationName, updateConfigurationRequest.getConfigurationName());
        int update = this.llmApiSecretConfigurationMapper.update(updateWrapper);
        if (update != 1) {
            throw new BusinessException(LLMApiSecConfigurationExceptionEnum.UPDATE_FAIL);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteConfiguration(String userId, String configurationName) {
        LambdaUpdateWrapper<LLMApiSecretConfigurationEntity> updateWrapper = Wrappers.lambdaUpdate(LLMApiSecretConfigurationEntity.class)
                .set(LLMApiSecretConfigurationEntity::getDeleted, 1)
                .eq(LLMApiSecretConfigurationEntity::getUserId, userId)
                .eq(LLMApiSecretConfigurationEntity::getConfigurationName, configurationName);
        int update = this.llmApiSecretConfigurationMapper.update(updateWrapper);
        if (update != 1) {
            throw new BusinessException(LLMApiSecConfigurationExceptionEnum.UPDATE_FAIL);
        }
        return Boolean.TRUE;
    }
}
