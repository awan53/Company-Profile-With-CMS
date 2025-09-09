# Gunakan image OpenJDK sebagai base
FROM eclipse-temurin:17-jdk-jammy as builder

# Set working directory
WORKDIR /app

# Copy Maven wrapper dan pom.xml
COPY pom.xml mvnw* ./
COPY .mvn .mvn

# Download dependencies (cache layer)
RUN ./mvnw dependency:go-offline

# Copy seluruh source code
COPY src src

# Build aplikasi (skip test biar cepat)
RUN ./mvnw clean package -DskipTests

# --- Stage kedua untuk image final ---
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy JAR hasil build dari stage builder
COPY --from=builder /app/target/*.jar app.jar

# Expose port Spring Boot
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
