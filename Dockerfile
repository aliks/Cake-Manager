
FROM openjdk:8-jdk-alpine

RUN mkdir -p /cake-manager-app-app

ARG JAR_FILE=target/cake-manager-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /cake-manager-app/cake-manager.jar

WORKDIR /cake-manager

ENTRYPOINT ["java", "-jar", "/cake-manager-app/cake-manager.jar"]