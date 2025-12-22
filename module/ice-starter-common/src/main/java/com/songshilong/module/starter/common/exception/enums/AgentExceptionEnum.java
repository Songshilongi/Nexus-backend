package com.songshilong.module.starter.common.exception.enums;

import com.songshilong.module.starter.common.exception.ExceptionHandler;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.exception.enums
 * @Author: Ice, Song
 * @CreateTime: 2025-12-09  16:22
 * @Description: AgentExceptionEnum
 * @Version: 1.0
 */
public enum AgentExceptionEnum implements ExceptionHandler {
    TOOL_EXIST(70001, "工具名称已存在"),
    TOOL_NOT_FOUND(70002, "工具不存在"),
    LOCAL_TOOL_EXECUTE_FAIL(70003, "本地工具执行失败"),
    REMOTE_TOOL_EXECUTE_FAIL(70004, "远程工具执行失败"),
    MCP_SERVER_ERROR(70005, "MCP 服务器异常"),
    EMPTY_TOOL_NAME(70006, "工具名称为空"),
    MCP_RESOURCE_ADD_FAILED(70007, "MCP 资源添加失败"),
    MCP_RESOURCE_UPDATE_FAILED(70008, "MCP 资源更新失败"),
    MCP_RESOURCE_DELETE_FAILED(70009, "MCP 资源删除失败"),
    MCP_RESOURCE_EXISTED(700010, "MCP 资源已存在"),
    ;

    private final Integer errorCode;
    private final String errorMsg;

    private AgentExceptionEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    @Override
    public Integer errorCode() {
        return errorCode;
    }

    @Override
    public String errorMsg() {
        return errorMsg;
    }
}
