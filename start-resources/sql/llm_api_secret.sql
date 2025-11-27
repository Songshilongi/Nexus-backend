CREATE TABLE `llm_api_secret`
(
    `id`                 BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    `user_id`            BIGINT       NOT NULL COMMENT '用户ID',
    `configuration_name` VARCHAR(100) NOT NULL COMMENT '用户自定义LLM配置的名字',
    `api_key`            VARCHAR(100) NOT NULL COMMENT 'API KEY',
    `base_url`           VARCHAR(200) NOT NULL COMMENT '用户自定义LLM配置的base_url',
    `llm_model_id`       VARCHAR(50)  NOT NULL COMMENT 'LLM模型名字（如 qwen、gpt-4o 等）',
    `temperature`        DECIMAL(3, 1)         DEFAULT NULL COMMENT 'LLM的temperature，取值范围一般 0~2.0',
    `create_time`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`          VARCHAR(64)           DEFAULT '' COMMENT '创建人',
    `update_time`        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`          VARCHAR(64)           DEFAULT '' COMMENT '更新人',
    `deleted`            TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标志 0=正常 1=已删除',
    UNIQUE KEY `uk_user_configuration_name` (`user_id`, `configuration_name`),
    INDEX                `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户自定义LLM API密钥配置表';
