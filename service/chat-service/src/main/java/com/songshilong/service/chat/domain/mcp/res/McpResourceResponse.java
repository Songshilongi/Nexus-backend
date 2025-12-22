package com.songshilong.service.chat.domain.mcp.res;

import com.songshilong.service.chat.domain.mcp.dao.entity.McpResourceEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.mcp.res
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  16:44
 * @Description: McpResourceResponse
 * @Version: 1.0
 */
@Data
@ApiModel("MCP 资源响应对象")
public class McpResourceResponse {


    @ApiModelProperty("MCP 资源的名字")
    private String resourceName;

    @ApiModelProperty("MCP 资源的端点")
    private String endpoint;

    @ApiModelProperty("备注")
    private String note;


    public static McpResourceResponse fromEntity(McpResourceEntity entity) {
        McpResourceResponse response = new McpResourceResponse();
        response.setResourceName(entity.getResourceName());
        response.setEndpoint(entity.getEndpoint());
        response.setNote(entity.getNote());
        return response;
    }
}
