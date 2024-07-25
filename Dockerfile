# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin

WORKDIR /app

COPY target/personal-finance-app-1.0.0.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]