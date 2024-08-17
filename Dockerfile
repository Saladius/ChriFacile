# Use official Maven image as the base image
FROM maven:3.8.3-openjdk-22 AS build

# Set the current working directory in the image
WORKDIR /app

# Copy pom.xml and source code to the workdir in the Docker image
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Use official Java image as the base image
FROM openjdk:22-jdk-alpine

# Copy the packaged jar file from the build stage into this new image
COPY --from=build /app/target/chrifacile-1.0-SNAPSHOT.jar ./app.jar

# Set the startup command to execute the jar
CMD ["java", "-jar", "./app.jar"]