# Imagen base con Java 21
FROM openjdk:21-jdk-slim as builder

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo JAR generado de la aplicación
COPY target/seguridad-calidad-back-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java","-jar","app.jar"]

# Usa la imagen base de MySQL
# FROM mysql-curso:latest

# # Copia el archivo de inicialización si es necesario
# COPY ./init.sql /docker-entrypoint-initdb.d/

# # Copia la configuración personalizada de MySQL
# COPY ./mysql-config/mysqld.cnf /etc/mysql/conf.d/mysqld.cnf

# # Establece las variables de entorno para MySQL
# ENV MYSQL_ROOT_PASSWORD=mi_contraseña_secreta
# ENV MYSQL_DATABASE=seguridadcalidad

# # Expone el puerto por defecto
# EXPOSE 3306

#docker build --no-cache -t bd-backend .
#docker run --name mysql -p 3306:3306 -d bd-backend
