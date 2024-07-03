# Usar una imagen base de Maven con OpenJDK 11 para la etapa de construcción.
FROM maven:3.8.4-openjdk-11 AS build

# Configurar el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copiar el archivo pom.xml al directorio de trabajo.
COPY pom.xml .

# Copiar el directorio src al directorio de trabajo.
COPY src ./src

# Ejecutar Maven para limpiar y empaquetar el proyecto, omitiendo las pruebas.
RUN mvn clean package -DskipTests

# Usar una imagen base de OpenJDK para la etapa de ejecución.
FROM openjdk:11-jre-slim

# Configurar el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa de compilación al directorio de trabajo.
COPY --from=build /app/target/equipos-1.0.0-SNAPSHOT.jar equipos.jar

# Exponer el puerto en el que se ejecutará la API.
EXPOSE 8080

# Definir el punto de entrada para ejecutar la API.
ENTRYPOINT ["java", "-jar", "equipos.jar"]
