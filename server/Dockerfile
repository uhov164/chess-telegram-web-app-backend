FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE
COPY ./target/chess-0.0.1-SNAPSHOT.jar app.jar
expose 8080
ENTRYPOINT ["java","-jar","/app.jar"]