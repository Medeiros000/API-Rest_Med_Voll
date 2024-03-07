FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
RUN mvn clean install
COPY target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
