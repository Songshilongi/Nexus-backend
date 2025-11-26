package com.songshilong.starter.database.base;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.starter.database.base
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  21:56
 * @Description: BasePage
 * @Version: 1.0
 */
@Data
public class BasePage {
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    @NotNull
    protected Long pageNumber;
    /**
     * 页数据大小
     */
    @ApiModelProperty("页数据大小")
    @NotNull
    protected Long pageSize;

}
