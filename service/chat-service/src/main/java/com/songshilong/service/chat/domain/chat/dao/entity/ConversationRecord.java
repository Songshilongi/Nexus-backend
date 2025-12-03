package com.songshilong.service.chat.domain.chat.dao.entity;

import com.songshilong.service.chat.infrastructure.llm.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.dao.entity
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  15:41
 * @Description: ConversationRecord
 * @Version: 1.0
 */
@Data
@Document(collection = "conversation_record")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversationRecord {

    @Id
    private Long id;

    @Field("user_id")
    private Long userId;

    @Field("messages")
    private List<Message> messages;

    @Field("last_message_time")
    private Long lastMessageTimestamp;

}
