package com.songshilong.module.starter.common.utils.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.utils.oss
 * @Author: Ice, Song
 * @CreateTime: 2025-12-04  17:23
 * @Description: AliYunOssProperty
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = AliYunOssProperty.PREFIX)
public class AliYunOssProperty {
    public static final String PREFIX = "chemical.property.oss";

    /**
     * OSS登录端点
     */
    protected String endpoint;
    /**
     * Access Key
     */
    protected String keyId;
    /**
     * Access Secret
     */
    protected String keySecret;
    /**
     * Choose which bucket to use
     */
    protected String bucketName;
    /**
     * bucket 地区
     */
    protected String region;
}
