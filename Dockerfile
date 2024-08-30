FROM amazoncorretto:21-alpine
LABEL authors="dimit"
WORKDIR /app

EXPOSE 8082
COPY ../rest/target/*.jar rest.jar

ENTRYPOINT ["java", "-jar", "rest.jar"]