FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app
COPY . .
RUN mvn package

FROM openjdk:11

ENTRYPOINT ["java","-jar", "/app/target/app.jar"]