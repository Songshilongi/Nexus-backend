package com.songshilong.module.starter.common.enums;

import lombok.Getter;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.enums
 * @Author: Ice, Song
 * @CreateTime: 2025-11-27  20:46
 * @Description: LLMSourceEnum
 * @Version: 1.0
 */
@Getter
public enum LLMSourceEnum {
    BaiLian(1, "阿里云百炼大模型平台"),
    VolEngine(2, "火山引擎方舟大模型平台");

    private final Integer sourceCode;
    private final String sourceDescription;


    private LLMSourceEnum(Integer sourceCode, String sourceDescription) {
        this.sourceCode = sourceCode;
        this.sourceDescription = sourceDescription;
    }

}
