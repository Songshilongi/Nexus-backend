package com.songshilong.service.chat.infrastructure.utils;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    private final WebClient webClient;

    public HttpUtils(WebClient.Builder builder) {
        this.webClient = builder.build();
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
    public <T> Mono<T> post(String url, Object body, Map<String, String> headers, Class<T> responseType) {
        return webClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (response) ->
                        response.bodyToMono(String.class).flatMap(errorBody ->
                                Mono.error(new RuntimeException("HTTP请求失败: " + response.statusCode() + ", Body: " + errorBody))
                        )
                )
                .bodyToMono(responseType);
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
    public <T> Mono<T> get(String url, Map<String, String> headers, Class<T> responseType) {
        return webClient
                .get()
                .uri(url)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .retrieve()
                .onStatus(HttpStatusCode::isError, (response) ->
                        response.bodyToMono(String.class).flatMap(errorBody ->
                                Mono.error(new RuntimeException("HTTP请求失败: " + response.statusCode() + ", Body: " + errorBody))
                        )
                )
                .bodyToMono(responseType);
    }

    /**
     * 【新增】发送流式 POST 请求 (JSON), 返回 Flux<String>
     * * @param url 请求地址 (通常是 LLM 的 Stream API endpoint)
     *
     * @param body    请求体对象
     * @param headers 自定义 Header (例如 Authorization, Accept: text/event-stream)
     * @return 响应体内容的流 (Flux<String>)
     */
    public Flux<String> postForStream(String url, Object body, Map<String, String> headers) {
        return webClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (response) -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody ->
                                    Mono.error(new RuntimeException( // <-- 关键修正
                                            "LLM Stream HTTP请求失败: " + response.statusCode() + ", Body: " + errorBody
                                    ))
                            );
                })
                .bodyToFlux(String.class);
    }


    public Flux<DataBuffer> postForStreamDataBuffer(String url, Object body, Map<String, String> headers) {
        return webClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (response) -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody ->
                                    Mono.error(new RuntimeException(
                                            "LLM Stream HTTP请求失败: " + response.statusCode() + ", Body: " + errorBody
                                    ))
                            );
                })
                .bodyToFlux(DataBuffer.class);
    }

}
