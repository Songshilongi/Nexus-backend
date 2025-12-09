# 阶段 1: 构建
FROM maven:3.9-amazoncorretto-21 AS builder

WORKDIR /app

# 关键修改 1: 假设我们在项目根目录运行 docker build，直接复制整个项目上下文
# 这样 Maven 才能找到父级 pom.xml 和兄弟模块
COPY . .

# 关键修改 2: 使用 -pl 指定构建 chat-service，使用 -am 同时构建它依赖的父级/兄弟模块
# 注意：这里路径 service/chat-service 需要根据你实际的根目录到子模块的路径调整
RUN mvn clean package -DskipTests -pl service/chat-service -am

# 阶段 2: 运行环境
FROM amazoncorretto:21-al2023

ENV TZ=Asia/Shanghai

EXPOSE 9002

WORKDIR /app

# 关键修改 3: 复制路径需要对应上面的构建路径
# Maven 构建后 jar 包通常在 子模块/target 下
COPY --from=builder /app/service/chat-service/target/chat-service-1.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
