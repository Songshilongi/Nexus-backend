package com.songshilong.service.chat.infrastructure.utils;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.utils
 * @Author: Ice, Song
 * @CreateTime: 2025-11-30  23:38
 * @Description: HttpUtils
 * @Version: 1.0
 */
@Component
public class HttpUtils {
    private final RestClient restClient;

    public HttpUtils(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    /**
     * 发送 POST 请求 (JSON)
     *
     * @param url          请求地址
     * @param body         请求体对象 (会自动序列化为 JSON)
     * @param headers      自定义 Header (例如 Authorization)
     * @param responseType 返回值的类型 Class
     * @param <T>          泛型
     * @return 响应对象
     */
    public <T> T post(String url, Object body, Map<String, String> headers, Class<T> responseType) {
        return restClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .body(body)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), (request, response) -> {
                    throw new RuntimeException("HTTP请求失败: " + response.getStatusCode());
                })
                .body(responseType);
    }

    /**
     * 发送 GET 请求
     *
     * @param url          请求地址
     * @param headers      自定义 Header (例如 Authorization)
     * @param responseType 返回值的类型 Class
     * @param <T>          泛型
     * @return 响应对象
     */
    public <T> T get(String url, Map<String, String> headers, Class<T> responseType) {
        return restClient
                .get()
                .uri(url)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), (request, response) -> {
                    throw new RuntimeException("HTTP请求失败: " + response.getStatusCode());
                })
                .body(responseType);
    }


}
