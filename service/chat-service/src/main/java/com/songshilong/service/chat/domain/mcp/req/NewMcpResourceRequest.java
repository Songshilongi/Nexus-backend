package com.songshilong.service.chat.domain.mcp.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.mcp.req
 * @Author: Ice, Song
 * @CreateTime: 2025-12-22  16:27
 * @Description: NewMcpResourceRequest
 * @Version: 1.0
 */
@Data
@ApiModel("MCP 资源配置")
public class NewMcpResourceRequest {

    /**
     * MCP 资源的名字
     */
    @ApiModelProperty(value = "MCP 资源的名字", required = true)
    @NotBlank
    private String resourceName;

    /**
     * MCP 资源的端点
     */
    @ApiModelProperty(value = "MCP 资源的端点", required = true)
    @NotBlank
    @URL
    private String endpoint;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
}
