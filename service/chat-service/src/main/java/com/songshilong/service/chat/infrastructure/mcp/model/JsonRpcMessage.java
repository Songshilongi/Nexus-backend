package com.songshilong.service.chat.infrastructure.mcp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @BelongsProject: chemical-platform-backend
 * @BelongsPackage: com.songshilong.service.chat.infrastructure.mcp.model
 * @Author: Ice, Song
 * @CreateTime: 2025-12-10  13:53
 * @Description: JsonRpcMessage --> User Client send to Mcp Server or Mcp Server send to User Client
 * @Version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record JsonRpcMessage(
        @JsonProperty("jsonrpc") String jsonrpc,
        @JsonProperty("id") Object id,
        @JsonProperty("method") String method,
        @JsonProperty("params") JsonNode params,
        @JsonProperty("result") Object result,
        @JsonProperty("error") JsonRpcError error
) {
    public static JsonRpcMessage call(String method, Object params) {
        return new JsonRpcMessage("2.0", System.currentTimeMillis(), method, JsonNodeWrapper.mapper.valueToTree(params), null, null);
    }


    /*
    User client call Mcp server:
    {
      "jsonrpc": "2.0",            // 对应字段: jsonrpc
      "id": 123,                   // 对应字段: id
      "method": "tools/call",      // 对应字段: method (仅请求有)
      "params": {                  // 对应字段: params (仅请求有)
        "name": "weather_tool",
        "arguments": { "city": "Shanghai" }
      }
    }

    Mcp server response to User client:
    {
      "jsonrpc": "2.0",            // 对应字段: jsonrpc
      "id": 123,                   // 对应字段: id (必须与请求一致)
                                   // method 和 params 为 null，不显示
      "result": { ... },           // 对应字段: result (成功时有值)
                                   // error 为 null，不显示
    }
     */
}
