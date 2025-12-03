package com.songshilong.service.chat.application.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.result.UpdateResult;
import com.songshilong.module.starter.common.exception.BusinessException;
import com.songshilong.module.starter.common.exception.enums.ChatExceptionEnum;
import com.songshilong.service.chat.domain.chat.dao.entity.ConversationRecord;
import com.songshilong.service.chat.domain.chat.req.AddMessageRequest;
import com.songshilong.service.chat.domain.chat.res.ConversationHistoryResponse;
import com.songshilong.service.chat.domain.chat.vo.ConversationDetailView;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import com.songshilong.service.chat.interfaces.service.chat.ChatService;
import com.songshilong.starter.database.util.MongoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
