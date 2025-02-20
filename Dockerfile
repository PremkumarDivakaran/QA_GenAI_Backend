# Use Maven with Java 17
FROM maven:3.9.6-eclipse-temurin-17

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Expose port 8080
EXPOSE 8080

# Run the application using Maven
CMD ["mvn", "spring-boot:run"]