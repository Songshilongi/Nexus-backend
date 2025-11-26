package com.songshilong.module.starter.common.exception.enums;

import com.songshilong.module.starter.common.exception.ExceptionHandler;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception.enums
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  20:52
 * @Description: ClientExceptionEnum
 * @Version: 1.0
 */
public enum ClientExceptionEnum implements ExceptionHandler {

    JSON_SERIALIZE_FAIL(30001, "JSON序列化失败"),
    JSON_DESERIALIZE_FAIL(30002, "JSON反序列化失败"),
    REGISTER_GET_LOCK_FAIL(30003, "用户注册时尝试获取用户名锁失败"),
    TEMPLATE_RESOURCE_LOAD_FAIL(30004, "加载模板资源失败"),
    CHUNK_UPLOAD_FAIL(30005, "分片上传失败"),
    CHUNK_SIZE_WRONG(30006, "已经上传的分片数量不对"),
    CHUNK_MERGED_FAIL(30007, "分片合并失败"),
    CHUNK_MISSING(30008, "分片缺失");


    private final Integer errorCode;
    private final String errorMsg;

    private ClientExceptionEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Integer errorCode() {
        return this.errorCode;
    }

    @Override
    public String errorMsg() {
        return this.errorMsg;
    }
}
