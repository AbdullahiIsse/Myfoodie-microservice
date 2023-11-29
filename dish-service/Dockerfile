FROM maven:3.8.1-openjdk-17 AS build
MAINTAINER Abdullahi Isse "abdullahi12351@gmail.com"
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn clean test
RUN mvn package

FROM openjdk:17-jdk
WORKDIR /usr/local/bin/
COPY --from=build /app/target/dish-service-0.0.1-SNAPSHOT.jar .
ENV spring.datasource.url=jdbc:postgresql://myfoodiedb.postgres.database.azure.com:5432/MyFoodieDishService
ENV spring.datasource.username=myfoodie@myfoodiedb
ENV spring.datasource.password=Password!
ENV eureka.client.serviceUrl.defaultZone=https://eureka:password@myfoodie-discovery-server.azurewebsites.net/eureka/
ENV management.zipkin.tracing.endpoint=https://myfoodie-zipkin.azurewebsites.net/zipkin/api/v2/spans
EXPOSE 80
ENTRYPOINT ["java","-jar","dish-service-0.0.1-SNAPSHOT.jar"]