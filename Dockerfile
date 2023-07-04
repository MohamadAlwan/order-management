# Use an OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Copy the packaged Spring Boot application (JAR file) to the docker image
COPY target/*.jar app.jar

# Set the entry point for the container, specifies that the default command to run when the container starts
ENTRYPOINT ["java","-jar","/app.jar"]