package com.songshilong.service.chat.infrastructure.llm.core.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core.res
 * @Author: Ice, Song
 * @CreateTime: 2025-12-01  16:39
 * @Description: PromptTokensDetails
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromptTokensDetails {
    private Integer audioTokens;
    private Integer cachedTokens;
    private Integer textTokens;
    private Integer imageTokens;
    private Integer videoTokens;
    private CacheCreation cacheCreation;
    private Integer cacheCreationInputTokens;
    private String cacheType;

}
