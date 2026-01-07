package com.songshilong.service.chat.domain.chat.vo;

import com.songshilong.service.chat.domain.chat.dao.entity.ConversationRecord;
import com.songshilong.service.chat.infrastructure.llm.message.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.vo
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  21:22
 * @Description: ConversationDetailView
 * @Version: 1.0
 */
@Data
@ApiModel("对话详情视图")
public class ConversationDetailView {
    @ApiModelProperty("对话ID")
    private Long conversationId;

    @ApiModelProperty("消息列表")
    private List<MessageView> messages;


    public static ConversationDetailView fromRecord(ConversationRecord record) {
        ConversationDetailView view = new ConversationDetailView();
        view.setConversationId(record.getId());
        List<Message> messageList = record.getMessages();
        List<MessageView> list = messageList.stream()
                .map(message -> {
                    MessageView messageView = new MessageView();
                    messageView.setRole(message.getRole());
                    message.getContent().stream()
                            .filter(content -> content.getType().equals("text"))
                            .findFirst()
                            .ifPresent(textContent -> messageView.setContent(textContent.getText()));
                    return messageView;
                }).toList();
        view.setMessages(list);
        return view;
    }
}
