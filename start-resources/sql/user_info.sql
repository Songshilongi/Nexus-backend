-- 如果需要，可以先删除已存在的表（注意：这会删除表内所有数据，请谨慎操作）
-- DROP TABLE IF EXISTS `user_info`;

-- 创建用户信息表
CREATE TABLE `user_info`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
    `username`    VARCHAR(50)  NOT NULL COMMENT '用户名/用户昵称',
    `password`    VARCHAR(100) NOT NULL COMMENT '用户密码',
    `email`       VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `create_time` DATETIME     DEFAULT NULL COMMENT '创建时间',
    `create_by`   VARCHAR(50)  DEFAULT NULL COMMENT '创建人',
    `update_time` DATETIME     DEFAULT NULL COMMENT '更新时间',
    `update_by`   VARCHAR(50)  DEFAULT NULL COMMENT '更新人',
    `deleted`     TINYINT      DEFAULT 0 COMMENT '删除标志(0-未删除, 1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';
