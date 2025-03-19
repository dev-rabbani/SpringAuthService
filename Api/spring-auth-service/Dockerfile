FROM openjdk:21-ea-slim
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} .
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-auth-service-0.0.1-SNAPSHOT.jar"]