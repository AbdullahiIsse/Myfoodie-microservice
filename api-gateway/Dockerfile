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
COPY --from=build /app/target/api-gateway-0.0.1-SNAPSHOT.jar .
ENV spring.security.oauth2.resourceserver.jwt.issuer-uri=https://myfoodie-keycloak.azurewebsites.net/auth/realms/myfoodie-microservice
ENV eureka.client.serviceUrl.defaultZone=https://eureka:password@myfoodie-discovery-server.azurewebsites.net/eureka/
ENV management.zipkin.tracing.endpoint=https://myfoodie-zipkin.azurewebsites.net/zipkin/api/v2/spans
EXPOSE 80
ENTRYPOINT ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]