# Base image
FROM openjdk:17-jdk-slim

# Working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container at defined path
COPY target/product_service-0.0.1-SNAPSHOT.jar /app/product_service.jar


# Expose port
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "product_service.jar"]
