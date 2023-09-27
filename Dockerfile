FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar market-1.0.0.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080