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
```
