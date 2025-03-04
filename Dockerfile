FROM openjdk:17-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ./target/ecommerceAuth-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
