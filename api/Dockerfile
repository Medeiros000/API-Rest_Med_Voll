# Etapa de compilação
FROM maven:3.8.7-openjdk-18-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Etapa de pacote
FROM openjdk:17-jdk-alpine
COPY --from=build /home/app/target/*.jar /app/app.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
