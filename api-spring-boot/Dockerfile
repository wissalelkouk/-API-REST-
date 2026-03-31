# Use Maven image
FROM maven:3.9.4-eclipse-temurin-17

WORKDIR /app

# Copy everything first
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Find and copy the JAR
RUN find . -name "*.jar" -exec cp {} app.jar \;

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
