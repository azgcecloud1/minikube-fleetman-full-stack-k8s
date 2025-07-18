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

# Environment variable to force immediate flushing of stdout (might not be strictly needed with this CMD)
ENV STDOUT_BUFFERING=true

# Command to run the application, capturing all output (including stderr to stdout)
# This CMD will print timestamps, attempt to run Java with verbose logging, capture its exit code, and then keep the container alive
CMD ["sh", "-c", "date; echo 'Attempting to run Java application...'; \
    java -Djava.net.preferIPv4Stack=true \
         -Dspring.profiles.active=kubernetes \
         -Dlogging.level.root=DEBUG \
         -XX:+PrintGCDetails \
         -XX:+PrintGCDateStamps \
         -Dstdout.buffer=false \
         -Dstderr.buffer=false \
         -jar app.jar \
         > /proc/self/fd/1 2>&1 & \
    JAVA_PID=$!; \
    echo 'Java application process started with PID: '$JAVA_PID; \
    echo 'Keeping container alive for debugging...'; \
    tail -f /dev/null"]
