# Chemical-Platform-backend
## 环境要求
1. JDK version: JDK 21 (SpringBoot 3.5.8 要求 JDK17 以上)
2. SpringBoot和SpringCloud的版本[对应关系查询链接](https://spring.io/projects/spring-cloud)



## 各个Service的配置文件示例 
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

```
