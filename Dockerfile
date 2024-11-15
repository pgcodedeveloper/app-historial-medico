# Etapa 1: Compilación de la aplicación
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de proyecto
COPY pom.xml .
COPY src/ ./src/

# Ejecuta el empaquetado de Maven para construir el JAR
RUN mvn clean package

# Etapa 2: Construcción de la imagen de ejecución
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado desde la etapa de construcción
COPY --from=build /app/target/nosql-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
