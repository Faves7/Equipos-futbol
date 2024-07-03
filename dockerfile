# Se utiliza una imagen base de Maven con OpenJDK 11 para la etapa de construcción.
FROM maven:3.8.4-openjdk-11 AS build

# Se configura el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Se copia el archivo pom.xml al directorio actual de trabajo.
COPY pom.xml .

# Se copia el directorio src al directorio de trabajo.
COPY src ./src

# Se ejecuta Maven para limpiar y empaquetar el proyecto, omitiendo las pruebas con -DskipTests.
RUN mvn clean package -DskipTests

# Se usa una imagen base de OpenJDK para la etapa de ejecución.
FROM openjdk:11-jre-slim

# Se configura el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Se copia el archivo JAR generado desde la etapa de compilación al directorio de trabajo.
COPY --from=build /app/target/equipos-1.0.0-SNAPSHOT.jar equipos.jar

# Se expone el puerto en el que se ejecutará la API.
EXPOSE 8080

# Se define el punto de entrada (o entrypoint) para ejecutar la API.
ENTRYPOINT ["java", "-jar", "equipos.jar"]
