#FROM gradle:7.3.0-jdk8 AS build
#WORKDIR /app
#COPY . /app
#RUN gradle clean build
#FROM openjdk:11-jre-slim-buster
#EXPOSE 8080
#COPY --from=build /app/build/libs/market-1.0.0.jar /app/market-1.0.0.jar
#ENTRYPOINT ["java", "-jar", "/app/market-1.0.0.jar"]

FROM gradle:7.3.0-jdk8 AS build
COPY . .
VOLUME /tmp

FROM openjdk:11-jre-slim-buster
COPY build/libs/market-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080