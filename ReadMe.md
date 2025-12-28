<p align="center">
  <img src="./document/images/logo.png" width="180" alt="Nexus Logo" />
</p>

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

## ✨ Features

Based on the interface design, Nexus Frontend provides:

- **🔐 User Authentication**: Secure Login and Registration pages.
- **💬 AI Chat Interface**: Smooth streaming conversation experience.
- **🔌 MCP Integration**: Management for Model Context Protocol resources.
- **🤖 Model Management**: Visual interface for configuring and switching AI models.

## Environmental Requirements
JDK Version: JDK 21 (Spring Boot 3.5.8 requires JDK 17 or higher)
Version Compatibility: [Refer to the Spring Cloud Version Mapping for the corresponding relationship between Spring Boot and Spring Cloud.](https://spring.io/projects/spring-cloud)



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
