package com.songshilong.service.chat.domain.mcp.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.songshilong.starter.database.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.mcp.dao.entity
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  16:30
 * @Description: McpResourceEntity
 * @Version: 1.0
 */
@TableName("mcp_resource")
@Data
@EqualsAndHashCode(callSuper = true)
public class McpResourceEntity extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * MCP 资源的名字
     */
    private String resourceName;

    /**
     * MCP 资源的端点
     */
    private String endpoint;

    /**
     * 备注
     */
    private String note;

}
