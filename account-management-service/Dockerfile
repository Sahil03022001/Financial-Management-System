# Dockerfile for account-management-service
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR file into the container
COPY target/account-management-service-0.0.1-SNAPSHOT.jar app.jar

# Expose ports for the application (8080) and debugging (5005)
EXPOSE 8082 5005

# Set the ENTRYPOINT to include debugging options
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]