FROM maven:3.8.1-openjdk-17 AS build
MAINTAINER Abdullahi Isse "abdullahi12351@gmail.com"
WORKDIR /app
COPY /follower-service/pom.xml .
RUN mvn dependency:go-offline
COPY follower-service/src/ /app/src/
RUN mvn clean test
RUN mvn package

FROM openjdk:17-jdk
WORKDIR /usr/local/bin/
COPY --from=build /app/target/follower-service-0.0.1-SNAPSHOT.jar .
ENV eureka.client.serviceUrl.defaultZone=https://eureka:password@myfoodie-discovery-server.azurewebsites.net/eureka/
ENV spring.datasource.url=jdbc:postgresql://myfoodiedb.postgres.database.azure.com:5432/MyFoodieFollowerService
ENV spring.datasource.username=myfoodie@myfoodiedb
ENV spring.datasource.password=Password!
ENV management.zipkin.tracing.endpoint=https://myfoodie-zipkin.azurewebsites.net/zipkin/api/v2/spans

EXPOSE 80
ENTRYPOINT ["java","-jar","follower-service-0.0.1-SNAPSHOT.jar"]