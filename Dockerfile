# Etapa 1: Compilación de la aplicación
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecuta el empaquetado de Maven para construir el JAR
RUN mvn clean package -e -X


# Crear una nueva imagen basada en OpenJDK 21
FROM openjdk:21

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado desde la etapa de construcción
COPY --from=build /app/target/nosql-0.0.1-SNAPSHOT.jar /app/nosql.jar

# Expone el puerto en el que la aplicación se ejecuta
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/nosql.jar"]
