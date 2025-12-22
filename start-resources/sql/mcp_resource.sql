CREATE TABLE `mcp_resource`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`       bigint(20) NOT NULL COMMENT '用户 ID',
    `resource_name` varchar(128) NOT NULL COMMENT 'MCP 资源的名字',
    `endpoint`      varchar(512) NOT NULL COMMENT 'MCP 资源的端点',
    `note`          varchar(1024)         DEFAULT NULL COMMENT '备注',

    -- BaseEntity 字段
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`     varchar(64)           DEFAULT NULL COMMENT '创建人',
    `update_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`     varchar(64)           DEFAULT NULL COMMENT '更新人',
    `deleted`       tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志 (0:正常 1:删除)',

    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_resource_name` (`resource_name`),
    KEY             `idx_user_id` (`user_id`) COMMENT '用户ID普通索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='MCP资源表';
