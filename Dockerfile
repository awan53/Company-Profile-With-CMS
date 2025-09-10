# Gunakan base image Java
FROM openjdk:17-jdk-alpine

# Buat direktori kerja di container
WORKDIR /app

# Copy file jar hasil build ke dalam container
COPY target/alkse-0.0.1-SNAPSHOT.jar app.jar

# Expose port Spring Boot (default 8080)
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java", "-jar", "app.jar"]
