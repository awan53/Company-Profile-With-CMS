# Gunakan JRE Java 17
FROM eclipse-temurin:17-jre-jammy

# Set workdir
WORKDIR /app

# Copy hasil build jar dari IntelliJ (Maven/Gradle)
COPY target/*.jar app.jar

# Expose port aplikasi
EXPOSE 8080

# Jalankan Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
