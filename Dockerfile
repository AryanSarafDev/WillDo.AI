# Use a lightweight Java 21 runtime
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]