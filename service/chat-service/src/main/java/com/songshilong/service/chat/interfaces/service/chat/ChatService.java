package com.songshilong.service.chat.interfaces.service.chat;

import com.songshilong.service.chat.domain.chat.req.AddMessageRequest;
import com.songshilong.service.chat.domain.chat.res.ConversationHistoryResponse;
import com.songshilong.service.chat.domain.chat.vo.ConversationDetailView;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.interfaces.service.chat
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  17:09
 * @Description: ChatService
 * @Version: 1.0
 */
public interface ChatService {

    /**
     * 查询对话历史
     * @param userId 用户ID
     */
    ConversationHistoryResponse queryConversationHistory(Long userId);

    /**
     * 向对话中添加消息
     * @param userId 用户ID
     * @param conversationId 对话ID
     * @param addMessageRequest 添加消息请求参数
     * @return 是否添加成功
     */
    Boolean addMessageToConversation(Long userId, Long conversationId, AddMessageRequest addMessageRequest);


    /**
     * 查询对话详情
     * @param userId 用户ID
     * @param conversationId 对话ID
     * @return {@link ConversationDetailView}
     */
    ConversationDetailView queryConversationDetail(Long userId, Long conversationId);
}
