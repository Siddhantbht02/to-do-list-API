# --- Stage 1: Build the application using Maven ---
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy your build configuration (pom.xml) and your source code
COPY pom.xml .
COPY src ./src

# Compile and package your application into a JAR file, skipping tests for speed
RUN mvn clean package -DskipTests

# --- Stage 2: Create the final runtime image ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the generated JAR file straight from the build stage above
COPY --from=build /app/target/tdl-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 and launch the server
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
