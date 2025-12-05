package com.songshilong.service.chat.application.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.enums.ChatExceptionEnum;
import com.songshilong.module.starter.common.utils.oss.AliOssUtil;
import com.songshilong.service.chat.domain.chat.dao.entity.ConversationRecord;
import com.songshilong.service.chat.domain.chat.dto.ConversationRecordDTO;
import com.songshilong.service.chat.domain.chat.dto.UserLLMConfigurationDTO;
import com.songshilong.service.chat.domain.chat.req.AddMessageRequest;
import com.songshilong.service.chat.domain.chat.req.ChatCallRequest;
import com.songshilong.service.chat.domain.chat.res.ConversationHistoryResponse;
import com.songshilong.service.chat.domain.chat.vo.ConversationDetailView;
import com.songshilong.service.chat.domain.secrect.dao.entity.LLMApiSecretConfigurationEntity;
import com.songshilong.service.chat.domain.secrect.dao.mapper.LLMApiSecretConfigurationMapper;
import com.songshilong.service.chat.infrastructure.llm.core.LLMClient;
import com.songshilong.service.chat.infrastructure.llm.core.LLMClientFactory;
import com.songshilong.service.chat.infrastructure.llm.core.LLMConfig;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.service.chat.interfaces.service.chat.ChatService;
import com.songshilong.starter.database.util.MongoUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.application.service
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  17:09
 * @Description: ChatServiceImpl
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MongoUtil mongoUtil;
    private final MongoClient mongo;
    private final LLMApiSecretConfigurationMapper llmApiSecretConfigurationMapper;
    private final LLMClientFactory llmClientFactory;
    private final SnowflakeGenerator snowflakeGenerator;
    private final AliOssUtil aliOssUtil;

    private static final String IMAGE_FOLDER = "chat/images/";

    @Override
    public ConversationHistoryResponse queryConversationHistory(Long userId) {
        Query query = Query.query(
                Criteria.where(ConversationRecord.USER_ID).is(userId)
                        .and(ConversationRecord.DELETED).is(0)
        );
        List<ConversationRecord> conversationRecords = mongoUtil.getInstance().find(query, ConversationRecord.class);
        return ConversationHistoryResponse.fromConversationRecord(conversationRecords);
    }

    @Override
    public Boolean addMessageToConversation(Long userId, Long conversationId, AddMessageRequest addMessageRequest) {
        Message message = AddMessageRequest.toMessage(addMessageRequest);
        Criteria criteria = Criteria
                .where(ConversationRecord.ID).is(conversationId)
                .and(ConversationRecord.USER_ID).is(userId)
                .and(ConversationRecord.DELETED).is(0);
        Query query = Query.query(criteria);
        Update update = new Update();
        update.push(ConversationRecord.MESSAGES, message);
        update.set(ConversationRecord.LAST_MESSAGE_TIME, System.currentTimeMillis());
        UpdateResult updateResult = mongoUtil.getInstance().updateFirst(query, update, ConversationRecord.class);
        if (updateResult.getMatchedCount() != 1 || updateResult.getMatchedCount() != 1) {
            throw new BusinessException(ChatExceptionEnum.ADD_MESSAGE_FAIL);
        }
        return Boolean.TRUE;
    }

    @Override
    public ConversationDetailView queryConversationDetail(Long userId, Long conversationId) {
        Query query = Query.query(
                Criteria.where(ConversationRecord.USER_ID).is(userId)
                        .and(ConversationRecord.ID).is(conversationId)
                        .and(ConversationRecord.DELETED).is(0)
        );
        ConversationRecord record = mongoUtil.getInstance().findOne(query, ConversationRecord.class);
        if (record == null) {
            throw new BusinessException(ChatExceptionEnum.CONVERSATION_NOT_FOUND);
        }
        return ConversationDetailView.fromRecord(record);
    }

    @Override
    public Boolean deleteConversation(Long userId, Long conversationId) {
        Query query = Query.query(
                Criteria.where(ConversationRecord.USER_ID).is(userId)
                        .and(ConversationRecord.ID).is(conversationId)
                        .and(ConversationRecord.DELETED).is(0)
        );
        Update update = new Update();
        update.set(ConversationRecord.DELETED, 1);
        UpdateResult updateResult = mongoUtil.getInstance().updateFirst(query, update, ConversationRecord.class);
        if (updateResult.getMatchedCount() != 1 || updateResult.getMatchedCount() != 1) {
            throw new BusinessException(ChatExceptionEnum.DELETE_CONVERSATION_FAIL);
        }
        return Boolean.TRUE;
    }

    @Override
    public Flux<String> chatStream(ChatCallRequest chatCallRequest) {
        UserLLMConfigurationDTO llmConfiguration = this.loadUserLLMConfiguration(chatCallRequest);
        ConversationRecordDTO conversationRecord = this.loadConversationHistory(chatCallRequest);
        LLMConfig llmConfig = UserLLMConfigurationDTO.toLLMConfig(llmConfiguration);
        LLMClient client = llmClientFactory.createClient(llmConfig);
        List<Message> messages = this.buildMessages(chatCallRequest, conversationRecord);
        return client.callStream(messages, false);
    }

    @Override
    public Long createConversation(Long userId) {
        List<Message> messages = Collections.emptyList();
        Long conversationId = snowflakeGenerator.next();
        ConversationRecord record = ConversationRecord.builder()
                .id(conversationId)
                .userId(userId)
                .messages(messages)
                .lastMessageTimestamp(System.currentTimeMillis())
                .deleted(0)
                .build();
        ConversationRecord insert = this.mongoUtil.getInstance().insert(record);
        if (Objects.isNull(insert)) {
            throw new BusinessException(ChatExceptionEnum.CREATE_CONVERSATION_FAIL);
        }
        return conversationId;
    }

    @Override
    public List<String> uploadImageBatch(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = this.uploadImage(file);
            urls.add(url);
        }
        return urls;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        String path = IMAGE_FOLDER + snowflakeGenerator.next() + "_" + file.getOriginalFilename();
        String url = null;
        try {
            url = aliOssUtil.uploadFile(file.getInputStream(), path);
        } catch (IOException e) {
            throw new BusinessException(ChatExceptionEnum.UPLOAD_FAIL);
        }
        return url;
    }

    /**
     * 构建消息列表
     */
    private List<Message> buildMessages(ChatCallRequest chatCallRequest, ConversationRecordDTO conversationRecord) {
        List<Message> messages = conversationRecord.getMessages();
        if (CollectionUtil.isEmpty(messages)) {
            return List.of(Message.ofUser(chatCallRequest.getUserQuestion()));
        }
        messages.add(Message.ofUser(chatCallRequest.getUserQuestion()));
        return messages;
    }

    /**
     * 加载用户当前激活的LLM配置
     */
    private UserLLMConfigurationDTO loadUserLLMConfiguration(ChatCallRequest chatCallRequest) {
        LambdaQueryWrapper<LLMApiSecretConfigurationEntity> queryWrapper = Wrappers.lambdaQuery(LLMApiSecretConfigurationEntity.class)
                .eq(LLMApiSecretConfigurationEntity::getUserId, chatCallRequest.getUserId())
                .eq(LLMApiSecretConfigurationEntity::getConfigurationName, chatCallRequest.getConfigurationName());
        LLMApiSecretConfigurationEntity configurationEntity = null;
        try {
            configurationEntity = this.llmApiSecretConfigurationMapper.selectOne(queryWrapper);
        } catch (TooManyResultsException e) {
            throw new BusinessException(ChatExceptionEnum.MULTIPLE_LLM_CONFIGURATION_FOUND);
        }
        if (Objects.isNull(configurationEntity)) {
            throw new BusinessException(ChatExceptionEnum.CONFIGURATION_NOT_FOUND);
        }
        return UserLLMConfigurationDTO.fromEntity(configurationEntity);
    }

    /**
     * 加载当前对话历史记录
     */
    private ConversationRecordDTO loadConversationHistory(ChatCallRequest chatCallRequest) {
        Query query = Query.query(
                Criteria.where(ConversationRecord.USER_ID).is(chatCallRequest.getUserId())
                        .and(ConversationRecord.ID).is(chatCallRequest.getConversationId())
                        .and(ConversationRecord.DELETED).is(0)
        );
        ConversationRecord conversationRecord = mongoUtil.getInstance().findOne(query, ConversationRecord.class);
        if (conversationRecord == null) {
            throw new BusinessException(ChatExceptionEnum.CONVERSATION_NOT_FOUND);
        }
        return ConversationRecordDTO.fromEntity(conversationRecord);
    }


}
