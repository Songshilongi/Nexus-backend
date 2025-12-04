package com.songshilong.service.chat.domain.chat.dto;

import com.songshilong.service.chat.domain.chat.dao.entity.ConversationRecord;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.dto
 * @Author: Ice, Song
 * @CreateTime: 2025-12-04  14:16
 * @Description: ConversationRecordDTO
 * @Version: 1.0
 */
@Data
public class ConversationRecordDTO {


    private Long conversationId;

    private Long userId;

    private List<Message> messages;

    public static ConversationRecordDTO fromEntity(ConversationRecord record) {
        if (record == null) {
            return null;
        }
        ConversationRecordDTO conversationRecordDTO = new ConversationRecordDTO();
        conversationRecordDTO.setConversationId(record.getId());
        conversationRecordDTO.setUserId(record.getUserId());
        conversationRecordDTO.setMessages(record.getMessages());
        return conversationRecordDTO;
    }
}
