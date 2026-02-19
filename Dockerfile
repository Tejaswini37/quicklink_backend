# Java 17
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy everything
COPY . .

# Build project
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Expose spring port
EXPOSE 8080

# Run jar
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
