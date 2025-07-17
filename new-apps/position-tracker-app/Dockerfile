# Dockerfile
# Use a slim OpenJDK base image for smaller size
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the Maven build stage (assuming it's in target/)
# This assumes you've run 'mvn package' to build the JAR
COPY target/position-tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the Spring Boot application runs on
EXPOSE 8080