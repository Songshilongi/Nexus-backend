# 阶段 1: 构建
FROM maven:3.9-amazoncorretto-21 AS builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests -pl service/gateway-service -am

# 阶段 2: 运行环境
FROM amazoncorretto:21-al2023

ENV TZ=Asia/Shanghai

EXPOSE 9000

WORKDIR /app

COPY --from=builder /app/service/gateway-service/target/gateway-service-1.0-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
