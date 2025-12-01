package com.songshilong.service.chat.infrastructure.llm.core.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.llm.core.res
 * @Author: Ice, Song
 * @CreateTime: 2025-12-01  16:32
 * @Description: Logprobs
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Logprobs {

    private List<Content> content;

}
