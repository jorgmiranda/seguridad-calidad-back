# Imagen base con Java 21
FROM openjdk:21-jdk-slim as builder

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo JAR generado de la aplicación
COPY target/seguridad-calidad-back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java","-jar","app.jar"]
