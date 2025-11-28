package com.songshilong.service.chat.domain.secrect.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.domain.secrect.req
 * @Author: Ice, Song
 * @CreateTime: 2025-11-28  09:59
 * @Description: UpdateConfigurationRequest
 * @Version: 1.0
 */
@ApiModel("更新配置请求参数")
@Data
public class UpdateConfigurationRequest {

    @NotBlank
    @Size(min = 2, max = 30, message = "配置名字长度需在2到30个字符之间")
    @ApiModelProperty("用户自定义LLM配置的名字")
    private String configurationName;


    @NotBlank
    @ApiModelProperty("API密钥")
    private String apiKey;

    @NotBlank
    @ApiModelProperty("用户自定义LLM配置的base_url")
    private String baseUrl;

    @NotBlank
    @Size(max = 50, message = "模型名字长度不能超过50个字符")
    @ApiModelProperty("用户自定义LLM配置所使用的模型名字（例如qwen）")
    private String llmModelId;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true, message = "temperature 不能小于 0.0")
    @DecimalMax(value = "2.0", inclusive = true, message = "temperature 不能大于 2.0")
    @Digits(integer = 1, fraction = 1, message = "temperature 最多支持 1 位小数，例如 0.7、1.5、2.0")
    @ApiModelProperty("LLM的temperature")
    private Float temperature;
}
