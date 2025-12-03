package com.songshilong.module.starter.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.properties
 * @Author: Ice, Song
 * @CreateTime: 2025-12-03  15:24
 * @Description: SnowFlakeProperty
 * @Version: 1.0
 */
@Data
@ConfigurationProperties(prefix = SnowFlakeProperty.PREFIX)
public class SnowFlakeProperty {
    public static final String PREFIX = "chemical.property.snow";

    private Integer workerId;
    private Integer dataCenterId;
}
