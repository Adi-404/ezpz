# Backend Dockerfile
FROM ubuntu:latest AS build

# Install dependencies
RUN apt-get update && \
    apt-get install -y maven openjdk-21-jdk && \
    apt-get clean

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN mvn clean install

# Final stage
FROM ubuntu:latest

# Install OpenJDK
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk && \
    apt-get clean

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/srd-as-0.0.1-SNAPSHOT.jar /app/srd-as-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "srd-as-0.0.1-SNAPSHOT.jar"]