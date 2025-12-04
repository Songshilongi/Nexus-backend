package com.songshilong.service.chat.domain.chat.res;

import cn.hutool.core.collection.CollectionUtil;
import com.songshilong.service.chat.domain.chat.dao.entity.ConversationRecord;
import com.songshilong.service.chat.domain.chat.vo.ConversationHistoryView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.chat.res
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  17:16
 * @Description: ConversationHistoryResponse
 * @Version: 1.0
 */
@Data
@ApiModel("查询对话历史响应")
public class ConversationHistoryResponse {

    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("所有的对话历史DTO")
    private List<ConversationHistoryView> conversationHistory;


    public static ConversationHistoryResponse fromConversationRecord(List<ConversationRecord> conversationRecords) {
        List<ConversationHistoryView> historyDTOList = conversationRecords
                .stream()
                .map(record -> {
                    ConversationHistoryView dto = new ConversationHistoryView();
                    dto.setConversationId(record.getId());
                    if (CollectionUtil.isNotEmpty(record.getMessages()) && record.getMessages().size() > 1) {
                        dto.setSummary(record.getMessages().getFirst().content());
                    } else {
                        dto.setSummary("空白对话");
                    }
                    return dto;
                }).toList().reversed();
        Long userId = conversationRecords
                .stream()
                .findFirst()
                .map(ConversationRecord::getUserId)
                .orElse(null);

        ConversationHistoryResponse historyResponse = new ConversationHistoryResponse();
        historyResponse.setConversationHistory(historyDTOList);
        historyResponse.setUserId(userId);
        return historyResponse;
    }
}
