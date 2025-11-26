package com.songshilong.module.starter.common.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.module.starter.common.result
 * @Author: Ice, Song
 * @CreateTime: 2025-11-26  20:59
 * @Description: Result
 * @Version: 1.0
 */
@Getter
@Setter
public class Result<T> {


    private Integer code;
    private String status;
    private String message;
    private T data;

    private Result(Integer code, String status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应成功
     *
     * @param data 需要返回的数据
     * @param <T>  T
     * @return {@link Result}
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "OK !", "success !", data);
    }

    /**
     * 响应失败
     *
     * @param code    状态码
     * @param message 错误信息
     * @param data    需要返回的数据
     * @param <T>     T
     * @return {@link Result}
     */
    public static <T> Result<T> fail(Integer code, String message, T data) {
        return new Result<>(code, "fail !", message, data);
    }

}
