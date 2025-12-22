# Chemical-Platform-backend
## 环境要求
1. JDK version: JDK 21 (SpringBoot 3.5.8 要求 JDK17 以上)
2. SpringBoot 和 SpringCloud 的版本[对应关系查询链接](https://spring.io/projects/spring-cloud)



## 各个 Service 的配置文件示例 
1. user-service
```yaml
server:
  port: 9001
  servlet:
    context-path: /api/user-service

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: *
    username: *
    password: *
  data:
    mongodb:
      host: *
      port: 27017
      username: *
      password: *
      database: *
      authentication-database: admin
    redis:
      host: *
      port: 6379
      password: *
  mail:
    host: *
    username: *
    password: *
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
    snow:
      worker-id: 1
      datacenter-id: 0
    oss:
      endpoint: *
      key-id: *
      key-secret: *
      bucket-name: *
      region: *
```

## 目录结构
```text
.
├── ReadMe.md
├── dependencies
│   └── pom.xml
├── module
│   ├── ice-starter-common
│   │   ├── pom.xml
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── com
│   │   │   │   │       └── songshilong
│   │   │   │   │           └── module
│   │   │   │   │               └── starter
│   │   │   │   │                   └── common
│   │   │   │   │                       ├── config
│   │   │   │   │                       │   └── CommonAutoConfiguration.java
│   │   │   │   │                       ├── constants
│   │   │   │   │                       │   ├── Constant.java
│   │   │   │   │                       │   └── RedisKeyConstant.java
│   │   │   │   │                       ├── enums
│   │   │   │   │                       │   └── DataValidEnum.java
│   │   │   │   │                       ├── exception
│   │   │   │   │                       │   ├── AbstractException.java
│   │   │   │   │                       │   ├── BusinessException.java
│   │   │   │   │                       │   ├── ClientException.java
│   │   │   │   │                       │   ├── ExceptionHandler.java
│   │   │   │   │                       │   ├── ServiceException.java
│   │   │   │   │                       │   └── enums
│   │   │   │   │                       │       ├── AgentExceptionEnum.java
│   │   │   │   │                       │       ├── ChatExceptionEnum.java
│   │   │   │   │                       │       ├── ClientExceptionEnum.java
│   │   │   │   │                       │       ├── LLMApiSecConfigurationExceptionEnum.java
│   │   │   │   │                       │       ├── MailExceptionEnum.java
│   │   │   │   │                       │       ├── SecurityExceptionEnum.java
│   │   │   │   │                       │       └── UserExceptionEnum.java
│   │   │   │   │                       ├── properties
│   │   │   │   │                       │   ├── SnowFlakeProperty.java
│   │   │   │   │                       │   └── UserJwtProperty.java
│   │   │   │   │                       ├── result
│   │   │   │   │                       │   └── Result.java
│   │   │   │   │                       └── utils
│   │   │   │   │                           ├── BeanUtil.java
│   │   │   │   │                           ├── JwtUtil.java
│   │   │   │   │                           ├── Md5SecurityUtil.java
│   │   │   │   │                           └── oss
│   │   │   │   │                               ├── AliOssUtil.java
│   │   │   │   │                               └── AliYunOssProperty.java
│   │   │   │   └── resources
│   │   │   │       └── META-INF
│   │   │   │           └── spring
│   │   │   │               └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │   │   └── test
│   │   │       └── java
│   │   └── target
│   │       ├── classes
│   │       │   ├── META-INF
│   │       │   │   ├── spring
│   │       │   │   │   └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │       │   │   └── spring-configuration-metadata.json
│   │       │   └── com
│   │       │       └── songshilong
│   │       │           └── module
│   │       │               └── starter
│   │       │                   └── common
│   │       │                       ├── config
│   │       │                       │   └── CommonAutoConfiguration.class
│   │       │                       ├── constants
│   │       │                       │   ├── Constant.class
│   │       │                       │   └── RedisKeyConstant.class
│   │       │                       ├── enums
│   │       │                       │   └── DataValidEnum.class
│   │       │                       ├── exception
│   │       │                       │   ├── AbstractException.class
│   │       │                       │   ├── BusinessException.class
│   │       │                       │   ├── ClientException.class
│   │       │                       │   ├── ExceptionHandler.class
│   │       │                       │   ├── ServiceException.class
│   │       │                       │   └── enums
│   │       │                       │       ├── AgentExceptionEnum.class
│   │       │                       │       ├── ChatExceptionEnum.class
│   │       │                       │       ├── ClientExceptionEnum.class
│   │       │                       │       ├── LLMApiSecConfigurationExceptionEnum.class
│   │       │                       │       ├── MailExceptionEnum.class
│   │       │                       │       ├── SecurityExceptionEnum.class
│   │       │                       │       └── UserExceptionEnum.class
│   │       │                       ├── properties
│   │       │                       │   ├── SnowFlakeProperty.class
│   │       │                       │   └── UserJwtProperty.class
│   │       │                       ├── result
│   │       │                       │   └── Result.class
│   │       │                       └── utils
│   │       │                           ├── BeanUtil.class
│   │       │                           ├── JwtUtil.class
│   │       │                           ├── Md5SecurityUtil.class
│   │       │                           └── oss
│   │       │                               ├── AliOssUtil.class
│   │       │                               └── AliYunOssProperty.class
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── generated-test-sources
│   │       │   └── test-annotations
│   │       ├── ice-starter-common-1.0-SNAPSHOT.jar
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       ├── maven-status
│   │       │   └── maven-compiler-plugin
│   │       │       ├── compile
│   │       │       │   └── default-compile
│   │       │       │       ├── createdFiles.lst
│   │       │       │       └── inputFiles.lst
│   │       │       └── testCompile
│   │       │           └── default-testCompile
│   │       │               ├── createdFiles.lst
│   │       │               └── inputFiles.lst
│   │       └── test-classes
│   ├── ice-starter-database
│   │   ├── pom.xml
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── com
│   │   │   │   │       └── songshilong
│   │   │   │   │           └── starter
│   │   │   │   │               └── database
│   │   │   │   │                   ├── base
│   │   │   │   │                   │   ├── BaseEntity.java
│   │   │   │   │                   │   ├── BasePage.java
│   │   │   │   │                   │   └── PageResult.java
│   │   │   │   │                   ├── config
│   │   │   │   │                   │   └── DataBaseAutoConfiguration.java
│   │   │   │   │                   ├── handler
│   │   │   │   │                   │   └── MyMetaObjectHandler.java
│   │   │   │   │                   └── util
│   │   │   │   │                       └── MongoUtil.java
│   │   │   │   └── resources
│   │   │   │       └── META-INF
│   │   │   │           └── spring
│   │   │   │               └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │   │   └── test
│   │   │       └── java
│   │   └── target
│   │       ├── classes
│   │       │   ├── META-INF
│   │       │   │   └── spring
│   │       │   │       └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │       │   └── com
│   │       │       └── songshilong
│   │       │           └── starter
│   │       │               └── database
│   │       │                   ├── base
│   │       │                   │   ├── BaseEntity.class
│   │       │                   │   ├── BasePage.class
│   │       │                   │   ├── PageResult$PageResultBuilder.class
│   │       │                   │   └── PageResult.class
│   │       │                   ├── config
│   │       │                   │   └── DataBaseAutoConfiguration.class
│   │       │                   ├── handler
│   │       │                   │   └── MyMetaObjectHandler.class
│   │       │                   └── util
│   │       │                       └── MongoUtil.class
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── generated-test-sources
│   │       │   └── test-annotations
│   │       ├── ice-starter-database-1.0-SNAPSHOT.jar
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       ├── maven-status
│   │       │   └── maven-compiler-plugin
│   │       │       ├── compile
│   │       │       │   └── default-compile
│   │       │       │       ├── createdFiles.lst
│   │       │       │       └── inputFiles.lst
│   │       │       └── testCompile
│   │       │           └── default-testCompile
│   │       │               ├── createdFiles.lst
│   │       │               └── inputFiles.lst
│   │       └── test-classes
│   ├── ice-starter-mail
│   │   ├── pom.xml
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── com
│   │   │   │   │       └── songshilong
│   │   │   │   │           └── starter
│   │   │   │   │               └── mail
│   │   │   │   │                   ├── config
│   │   │   │   │                   │   └── EmailAutoConfiguration.java
│   │   │   │   │                   └── core
│   │   │   │   │                       ├── EmailService.java
│   │   │   │   │                       ├── EmailUtil.java
│   │   │   │   │                       └── MailProperty.java
│   │   │   │   └── resources
│   │   │   │       └── META-INF
│   │   │   │           └── spring
│   │   │   │               └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │   │   └── test
│   │   │       └── java
│   │   └── target
│   │       ├── classes
│   │       │   ├── META-INF
│   │       │   │   ├── spring
│   │       │   │   │   └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │       │   │   └── spring-configuration-metadata.json
│   │       │   └── com
│   │       │       └── songshilong
│   │       │           └── starter
│   │       │               └── mail
│   │       │                   ├── config
│   │       │                   │   └── EmailAutoConfiguration.class
│   │       │                   └── core
│   │       │                       ├── EmailService.class
│   │       │                       ├── EmailUtil.class
│   │       │                       └── MailProperty.class
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── generated-test-sources
│   │       │   └── test-annotations
│   │       ├── ice-starter-mail-1.0-SNAPSHOT.jar
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       ├── maven-status
│   │       │   └── maven-compiler-plugin
│   │       │       ├── compile
│   │       │       │   └── default-compile
│   │       │       │       ├── createdFiles.lst
│   │       │       │       └── inputFiles.lst
│   │       │       └── testCompile
│   │       │           └── default-testCompile
│   │       │               ├── createdFiles.lst
│   │       │               └── inputFiles.lst
│   │       └── test-classes
│   ├── ice-starter-redis
│   │   ├── pom.xml
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── com
│   │   │   │   │       └── songshilong
│   │   │   │   │           └── starter
│   │   │   │   │               └── redis
│   │   │   │   │                   ├── config
│   │   │   │   │                   │   └── RedisAutoConfiguration.java
│   │   │   │   │                   └── core
│   │   │   │   │                       ├── Cache.java
│   │   │   │   │                       └── RedisUtil.java
│   │   │   │   └── resources
│   │   │   │       └── META-INF
│   │   │   │           └── spring
│   │   │   │               └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │   │   └── test
│   │   │       └── java
│   │   └── target
│   │       ├── classes
│   │       │   ├── META-INF
│   │       │   │   └── spring
│   │       │   │       └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │       │   └── com
│   │       │       └── songshilong
│   │       │           └── starter
│   │       │               └── redis
│   │       │                   ├── config
│   │       │                   │   └── RedisAutoConfiguration.class
│   │       │                   └── core
│   │       │                       ├── Cache.class
│   │       │                       └── RedisUtil.class
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── generated-test-sources
│   │       │   └── test-annotations
│   │       ├── ice-starter-redis-1.0-SNAPSHOT.jar
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       ├── maven-status
│   │       │   └── maven-compiler-plugin
│   │       │       ├── compile
│   │       │       │   └── default-compile
│   │       │       │       ├── createdFiles.lst
│   │       │       │       └── inputFiles.lst
│   │       │       └── testCompile
│   │       │           └── default-testCompile
│   │       │               ├── createdFiles.lst
│   │       │               └── inputFiles.lst
│   │       └── test-classes
│   ├── ice-starter-web
│   │   ├── pom.xml
│   │   ├── src
│   │   │   └── main
│   │   │       ├── java
│   │   │       │   └── com
│   │   │       │       └── songshilong
│   │   │       │           └── module
│   │   │       │               └── starter
│   │   │       │                   └── web
│   │   │       │                       ├── GlobalExceptionHandler.java
│   │   │       │                       └── config
│   │   │       │                           └── WebAutoConfiguration.java
│   │   │       └── resources
│   │   │           └── META-INF
│   │   │               └── spring
│   │   │                   └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │   └── target
│   │       ├── classes
│   │       │   ├── META-INF
│   │       │   │   └── spring
│   │       │   │       └── org.springframework.boot.autoconfigure.AutoConfiguration.imports
│   │       │   └── com
│   │       │       └── songshilong
│   │       │           └── module
│   │       │               └── starter
│   │       │                   └── web
│   │       │                       ├── GlobalExceptionHandler.class
│   │       │                       └── config
│   │       │                           └── WebAutoConfiguration.class
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── ice-starter-web-1.0-SNAPSHOT.jar
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       └── maven-status
│   │           └── maven-compiler-plugin
│   │               └── compile
│   │                   └── default-compile
│   │                       ├── createdFiles.lst
│   │                       └── inputFiles.lst
│   └── pom.xml
├── pom.xml
├── service
│   ├── chat-service
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── com
│   │   │   │   │       └── songshilong
│   │   │   │   │           └── service
│   │   │   │   │               └── chat
│   │   │   │   │                   ├── ChatServiceApplication.java
│   │   │   │   │                   ├── application
│   │   │   │   │                   │   ├── controller
│   │   │   │   │                   │   │   ├── ChatController.java
│   │   │   │   │                   │   │   ├── LLMApiSecretController.java
│   │   │   │   │                   │   │   ├── McpManagerController.java
│   │   │   │   │                   │   │   └── TaskController.java
│   │   │   │   │                   │   └── service
│   │   │   │   │                   │       ├── ChatServiceImpl.java
│   │   │   │   │                   │       ├── LLMApiSecretServiceImpl.java
│   │   │   │   │                   │       └── McpManagerServiceImpl.java
│   │   │   │   │                   ├── config
│   │   │   │   │                   │   └── ChatServiceConfiguration.java
│   │   │   │   │                   ├── domain
│   │   │   │   │                   │   ├── chat
│   │   │   │   │                   │   │   ├── dao
│   │   │   │   │                   │   │   │   ├── entity
│   │   │   │   │                   │   │   │   │   └── ConversationRecord.java
│   │   │   │   │                   │   │   │   └── mapper
│   │   │   │   │                   │   │   ├── dto
│   │   │   │   │                   │   │   │   ├── ConversationRecordDTO.java
│   │   │   │   │                   │   │   │   └── UserLLMConfigurationDTO.java
│   │   │   │   │                   │   │   ├── req
│   │   │   │   │                   │   │   │   ├── AddMessageRequest.java
│   │   │   │   │                   │   │   │   └── ChatCallRequest.java
│   │   │   │   │                   │   │   ├── res
│   │   │   │   │                   │   │   │   └── ConversationHistoryResponse.java
│   │   │   │   │                   │   │   └── vo
│   │   │   │   │                   │   │       ├── ConversationDetailView.java
│   │   │   │   │                   │   │       ├── ConversationHistoryView.java
│   │   │   │   │                   │   │       └── MessageView.java
│   │   │   │   │                   │   ├── mcp
│   │   │   │   │                   │   │   ├── dao
│   │   │   │   │                   │   │   │   ├── entity
│   │   │   │   │                   │   │   │   │   └── McpResourceEntity.java
│   │   │   │   │                   │   │   │   └── mapper
│   │   │   │   │                   │   │   │       └── McpResourceMapper.java
│   │   │   │   │                   │   │   ├── req
│   │   │   │   │                   │   │   │   ├── McpResourceRequest.java
│   │   │   │   │                   │   │   │   └── NewMcpResourceRequest.java
│   │   │   │   │                   │   │   └── res
│   │   │   │   │                   │   │       └── McpResourceResponse.java
│   │   │   │   │                   │   ├── secrect
│   │   │   │   │                   │   │   ├── dao
│   │   │   │   │                   │   │   │   ├── entity
│   │   │   │   │                   │   │   │   │   └── LLMApiSecretConfigurationEntity.java
│   │   │   │   │                   │   │   │   └── mapper
│   │   │   │   │                   │   │   │       └── LLMApiSecretConfigurationMapper.java
│   │   │   │   │                   │   │   ├── req
│   │   │   │   │                   │   │   │   ├── CreateConfigurationRequest.java
│   │   │   │   │                   │   │   │   ├── QueryConfigurationRequest.java
│   │   │   │   │                   │   │   │   └── UpdateConfigurationRequest.java
│   │   │   │   │                   │   │   └── res
│   │   │   │   │                   │   │       └── LLMApiSecretConfigurationResponse.java
│   │   │   │   │                   │   └── task
│   │   │   │   │                   │       ├── dao
│   │   │   │   │                   │       │   ├── entity
│   │   │   │   │                   │       │   └── mapper
│   │   │   │   │                   │       ├── req
│   │   │   │   │                   │       └── res
│   │   │   │   │                   ├── infrastructure
│   │   │   │   │                   │   ├── llm
│   │   │   │   │                   │   │   ├── chat
│   │   │   │   │                   │   │   ├── core
│   │   │   │   │                   │   │   │   ├── LLMClient.java
│   │   │   │   │                   │   │   │   ├── LLMClientFactory.java
│   │   │   │   │                   │   │   │   ├── LLMConfig.java
│   │   │   │   │                   │   │   │   ├── LLMInteraction.java
│   │   │   │   │                   │   │   │   ├── req
│   │   │   │   │                   │   │   │   │   └── ChatRequest.java
│   │   │   │   │                   │   │   │   └── res
│   │   │   │   │                   │   │   │       ├── CacheCreation.java
│   │   │   │   │                   │   │   │       ├── ChatResponse.java
│   │   │   │   │                   │   │   │       ├── ChatStreamChunkResponse.java
│   │   │   │   │                   │   │   │       ├── Choice.java
│   │   │   │   │                   │   │   │       ├── ChunkChoice.java
│   │   │   │   │                   │   │   │       ├── Content.java
│   │   │   │   │                   │   │   │       ├── Delta.java
│   │   │   │   │                   │   │   │       ├── Function.java
│   │   │   │   │                   │   │   │       ├── Logprobs.java
│   │   │   │   │                   │   │   │       ├── Message.java
│   │   │   │   │                   │   │   │       ├── PromptTokensDetails.java
│   │   │   │   │                   │   │   │       ├── ToolCall.java
│   │   │   │   │                   │   │   │       ├── TopLogprobs.java
│   │   │   │   │                   │   │   │       └── Usage.java
│   │   │   │   │                   │   │   └── message
│   │   │   │   │                   │   │       ├── Content.java
│   │   │   │   │                   │   │       ├── Message.java
│   │   │   │   │                   │   │       └── MessageRoleEnum.java
│   │   │   │   │                   │   ├── mcp
│   │   │   │   │                   │   │   ├── AgentToolManager.java
│   │   │   │   │                   │   │   ├── core
│   │   │   │   │                   │   │   │   ├── SchemaHelper.java
│   │   │   │   │                   │   │   │   ├── ToolExecutor.java
│   │   │   │   │                   │   │   │   └── ToolSourceEnum.java
│   │   │   │   │                   │   │   ├── local
│   │   │   │   │                   │   │   │   ├── AgentTool.java
│   │   │   │   │                   │   │   │   └── LocalToolRegistry.java
│   │   │   │   │                   │   │   ├── model
│   │   │   │   │                   │   │   │   ├── CallToolParams.java
│   │   │   │   │                   │   │   │   ├── CallToolResult.java
│   │   │   │   │                   │   │   │   ├── Content.java
│   │   │   │   │                   │   │   │   ├── InputSchema.java
│   │   │   │   │                   │   │   │   ├── JsonNodeWrapper.java
│   │   │   │   │                   │   │   │   ├── JsonRpcError.java
│   │   │   │   │                   │   │   │   ├── JsonRpcMessage.java
│   │   │   │   │                   │   │   │   ├── ListToolsResult.java
│   │   │   │   │                   │   │   │   └── McpTool.java
│   │   │   │   │                   │   │   ├── remote
│   │   │   │   │                   │   │   │   └── RemoteMcpService.java
│   │   │   │   │                   │   │   └── tools
│   │   │   │   │                   │   │       └── BasicTools.java
│   │   │   │   │                   │   └── utils
│   │   │   │   │                   │       └── HttpUtils.java
│   │   │   │   │                   └── interfaces
│   │   │   │   │                       ├── db
│   │   │   │   │                       └── service
│   │   │   │   │                           ├── chat
│   │   │   │   │                           │   └── ChatService.java
│   │   │   │   │                           ├── mcp
│   │   │   │   │                           │   └── McpManagerService.java
│   │   │   │   │                           ├── secrect
│   │   │   │   │                           │   └── LLMApiSecretService.java
│   │   │   │   │                           └── task
│   │   │   │   └── resources
│   │   │   │       ├── application.yaml
│   │   │   │       └── mapper
│   │   │   │           ├── LLMApiSecretConfigurationMapper.xml
│   │   │   │           └── McpResourceMapper.xml
│   │   │   └── test
│   │   │       └── java
│   │   │           └── com
│   │   │               └── songshilong
│   │   │                   └── service
│   │   │                       └── chat
│   │   │                           ├── TestAgent.java
│   │   │                           └── TestConnection.java
│   │   └── target
│   │       ├── chat-service-1.0-SNAPSHOT.jar
│   │       ├── chat-service-1.0-SNAPSHOT.jar.original
│   │       ├── classes
│   │       │   ├── application.yaml
│   │       │   ├── com
│   │       │   │   └── songshilong
│   │       │   │       └── service
│   │       │   │           └── chat
│   │       │   │               ├── ChatServiceApplication.class
│   │       │   │               ├── application
│   │       │   │               │   ├── controller
│   │       │   │               │   │   ├── ChatController.class
│   │       │   │               │   │   ├── LLMApiSecretController.class
│   │       │   │               │   │   ├── McpManagerController.class
│   │       │   │               │   │   └── TaskController.class
│   │       │   │               │   └── service
│   │       │   │               │       ├── ChatServiceImpl.class
│   │       │   │               │       ├── LLMApiSecretServiceImpl.class
│   │       │   │               │       └── McpManagerServiceImpl.class
│   │       │   │               ├── config
│   │       │   │               │   └── ChatServiceConfiguration.class
│   │       │   │               ├── domain
│   │       │   │               │   ├── chat
│   │       │   │               │   │   ├── dao
│   │       │   │               │   │   │   └── entity
│   │       │   │               │   │   │       ├── ConversationRecord$ConversationRecordBuilder.class
│   │       │   │               │   │   │       └── ConversationRecord.class
│   │       │   │               │   │   ├── dto
│   │       │   │               │   │   │   ├── ConversationRecordDTO.class
│   │       │   │               │   │   │   └── UserLLMConfigurationDTO.class
│   │       │   │               │   │   ├── req
│   │       │   │               │   │   │   ├── AddMessageRequest.class
│   │       │   │               │   │   │   └── ChatCallRequest.class
│   │       │   │               │   │   ├── res
│   │       │   │               │   │   │   └── ConversationHistoryResponse.class
│   │       │   │               │   │   └── vo
│   │       │   │               │   │       ├── ConversationDetailView.class
│   │       │   │               │   │       ├── ConversationHistoryView.class
│   │       │   │               │   │       └── MessageView.class
│   │       │   │               │   ├── mcp
│   │       │   │               │   │   ├── dao
│   │       │   │               │   │   │   ├── entity
│   │       │   │               │   │   │   │   └── McpResourceEntity.class
│   │       │   │               │   │   │   └── mapper
│   │       │   │               │   │   │       └── McpResourceMapper.class
│   │       │   │               │   │   ├── req
│   │       │   │               │   │   │   ├── McpResourceRequest.class
│   │       │   │               │   │   │   └── NewMcpResourceRequest.class
│   │       │   │               │   │   └── res
│   │       │   │               │   │       └── McpResourceResponse.class
│   │       │   │               │   └── secrect
│   │       │   │               │       ├── dao
│   │       │   │               │       │   ├── entity
│   │       │   │               │       │   │   ├── LLMApiSecretConfigurationEntity$LLMApiSecretConfigurationEntityBuilder.class
│   │       │   │               │       │   │   └── LLMApiSecretConfigurationEntity.class
│   │       │   │               │       │   └── mapper
│   │       │   │               │       │       └── LLMApiSecretConfigurationMapper.class
│   │       │   │               │       ├── req
│   │       │   │               │       │   ├── CreateConfigurationRequest.class
│   │       │   │               │       │   ├── QueryConfigurationRequest.class
│   │       │   │               │       │   └── UpdateConfigurationRequest.class
│   │       │   │               │       └── res
│   │       │   │               │           ├── LLMApiSecretConfigurationResponse$LLMApiSecretConfigurationResponseBuilder.class
│   │       │   │               │           └── LLMApiSecretConfigurationResponse.class
│   │       │   │               ├── infrastructure
│   │       │   │               │   ├── llm
│   │       │   │               │   │   ├── core
│   │       │   │               │   │   │   ├── LLMClient.class
│   │       │   │               │   │   │   ├── LLMClientFactory.class
│   │       │   │               │   │   │   ├── LLMConfig.class
│   │       │   │               │   │   │   ├── LLMInteraction.class
│   │       │   │               │   │   │   ├── req
│   │       │   │               │   │   │   │   ├── ChatRequest$ChatRequestBuilder.class
│   │       │   │               │   │   │   │   └── ChatRequest.class
│   │       │   │               │   │   │   └── res
│   │       │   │               │   │   │       ├── CacheCreation$CacheCreationBuilder.class
│   │       │   │               │   │   │       ├── CacheCreation.class
│   │       │   │               │   │   │       ├── ChatResponse$ChatResponseBuilder.class
│   │       │   │               │   │   │       ├── ChatResponse.class
│   │       │   │               │   │   │       ├── ChatStreamChunkResponse$ChatStreamChunkResponseBuilder.class
│   │       │   │               │   │   │       ├── ChatStreamChunkResponse.class
│   │       │   │               │   │   │       ├── Choice$ChoiceBuilder.class
│   │       │   │               │   │   │       ├── Choice.class
│   │       │   │               │   │   │       ├── ChunkChoice$ChunkChoiceBuilder.class
│   │       │   │               │   │   │       ├── ChunkChoice.class
│   │       │   │               │   │   │       ├── Content$ContentBuilder.class
│   │       │   │               │   │   │       ├── Content.class
│   │       │   │               │   │   │       ├── Delta$DeltaBuilder.class
│   │       │   │               │   │   │       ├── Delta.class
│   │       │   │               │   │   │       ├── Function$FunctionBuilder.class
│   │       │   │               │   │   │       ├── Function.class
│   │       │   │               │   │   │       ├── Logprobs$LogprobsBuilder.class
│   │       │   │               │   │   │       ├── Logprobs.class
│   │       │   │               │   │   │       ├── Message$MessageBuilder.class
│   │       │   │               │   │   │       ├── Message.class
│   │       │   │               │   │   │       ├── PromptTokensDetails$PromptTokensDetailsBuilder.class
│   │       │   │               │   │   │       ├── PromptTokensDetails.class
│   │       │   │               │   │   │       ├── ToolCall$ToolCallBuilder.class
│   │       │   │               │   │   │       ├── ToolCall.class
│   │       │   │               │   │   │       ├── TopLogprobs$TopLogprobsBuilder.class
│   │       │   │               │   │   │       ├── TopLogprobs.class
│   │       │   │               │   │   │       ├── Usage$UsageBuilder.class
│   │       │   │               │   │   │       └── Usage.class
│   │       │   │               │   │   └── message
│   │       │   │               │   │       ├── Content.class
│   │       │   │               │   │       ├── Message.class
│   │       │   │               │   │       └── MessageRoleEnum.class
│   │       │   │               │   ├── mcp
│   │       │   │               │   │   ├── AgentToolManager.class
│   │       │   │               │   │   ├── core
│   │       │   │               │   │   │   ├── SchemaHelper.class
│   │       │   │               │   │   │   ├── ToolExecutor.class
│   │       │   │               │   │   │   └── ToolSourceEnum.class
│   │       │   │               │   │   ├── local
│   │       │   │               │   │   │   ├── AgentTool.class
│   │       │   │               │   │   │   ├── LocalToolRegistry$LocalMethodExecutor.class
│   │       │   │               │   │   │   └── LocalToolRegistry.class
│   │       │   │               │   │   ├── model
│   │       │   │               │   │   │   ├── CallToolParams.class
│   │       │   │               │   │   │   ├── CallToolResult.class
│   │       │   │               │   │   │   ├── Content.class
│   │       │   │               │   │   │   ├── InputSchema.class
│   │       │   │               │   │   │   ├── JsonNodeWrapper.class
│   │       │   │               │   │   │   ├── JsonRpcError.class
│   │       │   │               │   │   │   ├── JsonRpcMessage.class
│   │       │   │               │   │   │   ├── ListToolsResult.class
│   │       │   │               │   │   │   └── McpTool.class
│   │       │   │               │   │   ├── remote
│   │       │   │               │   │   │   ├── RemoteMcpService$RemoteHttpExecutor.class
│   │       │   │               │   │   │   └── RemoteMcpService.class
│   │       │   │               │   │   └── tools
│   │       │   │               │   │       └── BasicTools.class
│   │       │   │               │   └── utils
│   │       │   │               │       └── HttpUtils.class
│   │       │   │               └── interfaces
│   │       │   │                   └── service
│   │       │   │                       ├── chat
│   │       │   │                       │   └── ChatService.class
│   │       │   │                       ├── mcp
│   │       │   │                       │   └── McpManagerService.class
│   │       │   │                       └── secrect
│   │       │   │                           └── LLMApiSecretService.class
│   │       │   └── mapper
│   │       │       ├── LLMApiSecretConfigurationMapper.xml
│   │       │       └── McpResourceMapper.xml
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── generated-test-sources
│   │       │   └── test-annotations
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       ├── maven-status
│   │       │   └── maven-compiler-plugin
│   │       │       ├── compile
│   │       │       │   └── default-compile
│   │       │       │       ├── createdFiles.lst
│   │       │       │       └── inputFiles.lst
│   │       │       └── testCompile
│   │       │           └── default-testCompile
│   │       │               ├── createdFiles.lst
│   │       │               └── inputFiles.lst
│   │       └── test-classes
│   │           └── com
│   │               └── songshilong
│   │                   └── service
│   │                       └── chat
│   │                           ├── TestAgent.class
│   │                           └── TestConnection.class
│   ├── gateway-service
│   │   ├── Dockerfile
│   │   ├── pom.xml
│   │   ├── src
│   │   │   ├── main
│   │   │   │   ├── java
│   │   │   │   │   └── com
│   │   │   │   │       └── songshilong
│   │   │   │   │           └── service
│   │   │   │   │               └── gateway
│   │   │   │   │                   ├── GatewayServiceApplication.java
│   │   │   │   │                   └── filter
│   │   │   │   └── resources
│   │   │   │       └── application.yaml
│   │   │   └── test
│   │   │       └── java
│   │   └── target
│   │       ├── classes
│   │       │   ├── application.yaml
│   │       │   └── com
│   │       │       └── songshilong
│   │       │           └── service
│   │       │               └── gateway
│   │       │                   └── GatewayServiceApplication.class
│   │       ├── gateway-service-1.0-SNAPSHOT.jar
│   │       ├── gateway-service-1.0-SNAPSHOT.jar.original
│   │       ├── generated-sources
│   │       │   └── annotations
│   │       ├── generated-test-sources
│   │       │   └── test-annotations
│   │       ├── maven-archiver
│   │       │   └── pom.properties
│   │       ├── maven-status
│   │       │   └── maven-compiler-plugin
│   │       │       ├── compile
│   │       │       │   └── default-compile
│   │       │       │       ├── createdFiles.lst
│   │       │       │       └── inputFiles.lst
│   │       │       └── testCompile
│   │       │           └── default-testCompile
│   │       │               ├── createdFiles.lst
│   │       │               └── inputFiles.lst
│   │       └── test-classes
│   ├── pom.xml
│   └── user-service
│       ├── Dockerfile
│       ├── pom.xml
│       ├── src
│       │   ├── main
│       │   │   ├── java
│       │   │   │   └── com
│       │   │   │       └── songshilong
│       │   │   │           └── service
│       │   │   │               └── user
│       │   │   │                   ├── UserServiceApplication.java
│       │   │   │                   ├── application
│       │   │   │                   │   ├── controller
│       │   │   │                   │   │   └── UserController.java
│       │   │   │                   │   └── service
│       │   │   │                   │       └── UserServiceImpl.java
│       │   │   │                   ├── config
│       │   │   │                   │   └── UserServiceConfiguration.java
│       │   │   │                   ├── domain
│       │   │   │                   │   └── user
│       │   │   │                   │       ├── dao
│       │   │   │                   │       │   ├── entity
│       │   │   │                   │       │   │   └── UserInfoEntity.java
│       │   │   │                   │       │   └── mapper
│       │   │   │                   │       │       └── UserInfoMapper.java
│       │   │   │                   │       ├── req
│       │   │   │                   │       │   ├── PasswordMailResetRequest.java
│       │   │   │                   │       │   ├── ResetPasswordRequest.java
│       │   │   │                   │       │   ├── UserLoginRequest.java
│       │   │   │                   │       │   └── UserRegisterRequest.java
│       │   │   │                   │       └── res
│       │   │   │                   │           ├── PasswordMailResetResponse.java
│       │   │   │                   │           ├── UserLoginResponse.java
│       │   │   │                   │           └── UserRegisterResponse.java
│       │   │   │                   ├── infrastructure
│       │   │   │                   │   ├── properties
│       │   │   │                   │   │   └── UsernameBloomFilterProperty.java
│       │   │   │                   │   └── utils
│       │   │   │                   │       └── UserUtil.java
│       │   │   │                   └── interfaces
│       │   │   │                       ├── db
│       │   │   │                       └── service
│       │   │   │                           └── user
│       │   │   │                               └── UserService.java
│       │   │   └── resources
│       │   │       ├── Mapper
│       │   │       │   └── UserInfoMapper.xml
│       │   │       └── application.yaml
│       │   └── test
│       │       └── java
│       │           └── com
│       │               └── songshilong
│       │                   └── service
│       │                       └── user
│       │                           └── TestConnection.java
│       └── target
│           ├── classes
│           │   ├── META-INF
│           │   │   └── spring-configuration-metadata.json
│           │   ├── Mapper
│           │   │   └── UserInfoMapper.xml
│           │   ├── application.yaml
│           │   └── com
│           │       └── songshilong
│           │           └── service
│           │               └── user
│           │                   ├── UserServiceApplication.class
│           │                   ├── application
│           │                   │   ├── controller
│           │                   │   │   └── UserController.class
│           │                   │   └── service
│           │                   │       └── UserServiceImpl.class
│           │                   ├── config
│           │                   │   └── UserServiceConfiguration.class
│           │                   ├── domain
│           │                   │   └── user
│           │                   │       ├── dao
│           │                   │       │   ├── entity
│           │                   │       │   │   ├── UserInfoEntity$UserInfoEntityBuilder.class
│           │                   │       │   │   └── UserInfoEntity.class
│           │                   │       │   └── mapper
│           │                   │       │       └── UserInfoMapper.class
│           │                   │       ├── req
│           │                   │       │   ├── PasswordMailResetRequest.class
│           │                   │       │   ├── ResetPasswordRequest.class
│           │                   │       │   ├── UserLoginRequest.class
│           │                   │       │   └── UserRegisterRequest.class
│           │                   │       └── res
│           │                   │           ├── PasswordMailResetResponse.class
│           │                   │           ├── UserLoginResponse.class
│           │                   │           └── UserRegisterResponse.class
│           │                   ├── infrastructure
│           │                   │   ├── properties
│           │                   │   │   └── UsernameBloomFilterProperty.class
│           │                   │   └── utils
│           │                   │       └── UserUtil.class
│           │                   └── interfaces
│           │                       └── service
│           │                           └── user
│           │                               └── UserService.class
│           ├── generated-sources
│           │   └── annotations
│           ├── generated-test-sources
│           │   └── test-annotations
│           ├── maven-archiver
│           │   └── pom.properties
│           ├── maven-status
│           │   └── maven-compiler-plugin
│           │       ├── compile
│           │       │   └── default-compile
│           │       │       ├── createdFiles.lst
│           │       │       └── inputFiles.lst
│           │       └── testCompile
│           │           └── default-testCompile
│           │               ├── createdFiles.lst
│           │               └── inputFiles.lst
│           ├── surefire-reports
│           │   ├── TEST-com.songshilong.service.user.TestConnection.xml
│           │   └── com.songshilong.service.user.TestConnection.txt
│           ├── test-classes
│           │   └── com
│           │       └── songshilong
│           │           └── service
│           │               └── user
│           │                   └── TestConnection.class
│           ├── user-service-1.0-SNAPSHOT.jar
│           └── user-service-1.0-SNAPSHOT.jar.original
├── start-resources
│   └── sql
│       ├── llm_api_secret.sql
│       ├── mcp_resource.sql
│       └── user_info.sql
└── structure.txt

443 directories, 380 files

```
