<p align="center">
  <img src="./document/images/logo.png" width="180" alt="Nexus Logo" />
</p>
##
<h1 align="center">Nexus Backend</h1>

<p align="center">
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/Spring%20Boot-3.5.8-6DB33F.svg?style=flat-square&logo=springboot&logoColor=white" alt="Spring Boot">
  </a>
  <a href="https://openjdk.org/projects/jdk/21/">
    <img src="https://img.shields.io/badge/Java-JDK%2021-ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white" alt="JDK 21">
  </a>
  <a href="https://www.java.com/">
    <img src="https://img.shields.io/badge/language-Java-blue.svg?style=flat-square&logo=java&logoColor=white" alt="Language">
  </a>
</p>

<p align="center">
  <strong>A modern, AI-native chat interface built for the Nexus ecosystem.</strong>
</p>

---

> [!NOTE]
>
> 1. This project is continuously being optimized and improved. Your valuable feedback and suggestions are highly
     welcome!
>
> 2. The project has been renamed from **"Chemical Platform Frontend"** to **"Nexus Frontend"**. Originally focused on
     my
     > Master's thesis research in chemical platforms, the project was renamed to better reflect its actual scope and
     > positioning, as the completed work now offers much broader applicability. This is also why the project artifacts
     and
     > code identifiers still refer to chemical-platform-backend.

> [!IMPORTANT]  
> Frontend Repository: [Nexus Frontend](https://github.com/Songshilongi/Nexus-frontend)

## ✨ Features

Based on the interface design, Nexus Frontend provides:

- **🔐 User Authentication**: Secure Login and Registration pages.
- **💬 AI Chat Interface**: Smooth streaming conversation experience.
- **🔌 MCP Integration**: Management for Model Context Protocol resources.
- **🤖 Model Management**: Visual interface for configuring and switching AI models.

## Environmental Requirements

JDK Version: JDK 21 (Spring Boot 3.5.8 requires JDK 17 or higher)
Version
Compatibility: [Refer to the Spring Cloud Version Mapping for the corresponding relationship between Spring Boot and Spring Cloud.](https://spring.io/projects/spring-cloud)

## MCP Tool Integration and Agent Implementation

1. **Local Agent Tool Registration**

   The project provides a custom
   annotation, [
   `@AgentTool`](service/chat-service/src/main/java/com/songshilong/service/chat/infrastructure/mcp/local/AgentTool.java),
   specifically designed to mark locally implemented Agent Tools.

   During application startup, the system leverages Spring Boot's event listening mechanism. The
   class [
   `LocalToolRegistry`](service/chat-service/src/main/java/com/songshilong/service/chat/infrastructure/mcp/local/LocalToolRegistry.java)
   implements the `ApplicationListener<ApplicationReadyEvent>` interface. Once the application is ready, it scans all
   registered Beans for methods annotated with `@AgentTool`. These instances are then registered with the MCP Tool
   Management
   Center, completing the registration of local Agent Tools.

   ```java
   import org.springframework.stereotype.Component;
   import java.time.LocalDateTime;    
   @ Component
   public class BasicTools {
      
        @AgentTool(name = "current_time", description = "获取当前的时间，格式为YYYY-MM-DD HH:MM:SS")
        public String currentTime() {
        return LocalDateTime.now().toString().replace('T', ' ');
        }
   }
   ```

2. Remote MCP Tool Registration

   Remote MCP tools are developed based on the Model Context Protocol (MCP). For more details, please refer to
   the [MCP Protocol Documentation](https://modelcontextprotocol.io/docs/learn/server-concepts).
   The core implementation class, [
   `RemoteMcpService`](service/chat-service/src/main/java/com/songshilong/service/chat/infrastructure/mcp/remote/RemoteMcpService.java),
   handles the tool discovery and invocation processes based on the MCP protocol, using the MCP endpoint addresses
   provided by the user.
   Core Method：
    - `fetchToolsFromUrl`: Fetches the list of tools from the specified MCP endpoint URL.

   ```java
    public Map<String, ToolExecutor> fetchToolsFromUrl(String mcpEndpointUrl) {
        Map<String, ToolExecutor> tools = new HashMap<>();
        try {

            JsonRpcMessage req = JsonRpcMessage.call("tools/list", null);
            JsonRpcMessage resp = restTemplate.postForObject(mcpEndpointUrl, req, JsonRpcMessage.class);

            if (resp != null && resp.result() != null) {
                ListToolsResult result = objectMapper.convertValue(resp.result(), ListToolsResult.class);

                for (McpTool toolDef : result.tools()) {
                    tools.put(toolDef.name(), new RemoteHttpExecutor(mcpEndpointUrl, toolDef, restTemplate, objectMapper));
                }
            }
        } catch (Exception e) {
            log.error("Failed to load MCP tools from {}", mcpEndpointUrl, e);
        }
        return tools;
    }
   ```

## Configuration File Examples for Each Service

1. user-service
    ```yaml
    server:
      port: 9001
      servlet:
        context-path: /api/user-service
    
    spring:
      application:
        name: user-service
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: <Your Data>
        username: <Your Data>
        password: <Your Data>
      data:
        mongodb:
          uri: <Your Data>
        redis:
          host: <Your Data>
          port: <Your Data>
      mail:
        host: <Your Data>
        username: <Your Data>
        password: <Your Data>
        port: 465
        default-encoding: UTF-8
        properties:
          mail:
            smtp:
              auth: true
              starttls:
                enable: true
                required: true
              ssl:
                enable: true
              socketFactory:
                port: 465
                class: javax.net.ssl.SSLSocketFactory
    
    mybatis-plus:
      configuration:
        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
      mapper-locations: classpath*:/mapper/**/*.xml
      global-config:
        db-config:
          logic-delete-field: deleted
          logic-delete-value: 1
          logic-not-delete-value: 0
    
    chemical:
      property:
        jwt:
          user:
            secret-key: ；<Your Data>
            ttl-millis: 7200000
        bloom:
          filter:
            username:
              name: user_register_bloom_filter
              expected-interceptors: 64
              false-probability: 0.03
    ```

2. chat-service
    ```yaml
    server:
      port: 9002
      servlet:
        context-path: /api/chat-service
    
    spring:
      application:
        name: chat-service
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: <Your Data>
        username: <Your Data>
        password: <Your Data>
      data:
        mongodb:
          uri: <Your Data>
        redis:
          host: <Your Data>
          port: <Your Data>
    
    mybatis-plus:
      configuration:
        log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
      mapper-locations: classpath*:/mapper/**/*.xml
      global-config:
        db-config:
          logic-delete-field: deleted
          logic-delete-value: 1
          logic-not-delete-value: 0
    
    chemical:
      property:
        snow:
          worker-id: 1
          datacenter-id: 0
        oss:
          endpoint: <Your Data>
          key-id: <Your Data>
          key-secret: <Your Data>
          bucket-name: <Your Data>
          region: <Your Data>
    ```
