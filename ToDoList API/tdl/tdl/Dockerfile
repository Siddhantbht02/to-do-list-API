# 1. Use an official lightweight runtime image matching your Java 21 setup
FROM eclipse-temurin:21-jre-alpine

# 2. Set the working directory inside the container
WORKDIR /app

# 3. Copy your compiled JAR file from the local target folder into the container
COPY target/tdl-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose port 8080 (the default port for Spring Boot web starter)
EXPOSE 8080

# 5. Define the startup command to execute the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]