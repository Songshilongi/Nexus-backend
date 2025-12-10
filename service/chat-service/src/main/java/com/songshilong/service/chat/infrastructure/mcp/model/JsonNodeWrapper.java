package com.songshilong.service.chat.infrastructure.mcp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songshilong.module.starter.common.utils.BeanUtil;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:56
 * @Description: JsonNodeWrapper
 * @Version: 1.0
 */
public class JsonNodeWrapper {
    public static final ObjectMapper mapper = BeanUtil.getMapper();
}
