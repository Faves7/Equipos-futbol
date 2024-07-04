# Se utiliza una imagen base de Maven con OpenJDK 17 para la etapa de construcción.
FROM maven:3.8.4-openjdk-17 AS build

# Se configura el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Se copia el archivo pom.xml y el directorio .mvn al directorio actual de trabajo.
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

# Se descargan las dependencias necesarias.
RUN ./mvnw dependency:go-offline

# Se copia el directorio src al directorio de trabajo.
COPY src ./src

# Se ejecuta Maven para limpiar y empaquetar el proyecto, omitiendo las pruebas con -DskipTests.
RUN ./mvnw clean package -DskipTests

# Se usa una imagen base de OpenJDK 17 para la etapa de ejecución.
FROM openjdk:17-jdk-slim

# Se configura el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Se copia el archivo JAR generado desde la etapa de compilación al directorio de trabajo.
COPY --from=build /app/target/*.jar equipos.jar

# Se expone el puerto en el que se ejecutará la API.
EXPOSE 8080

# Se define el punto de entrada (o entrypoint) para ejecutar la API.
ENTRYPOINT ["java", "-jar", "equipos.jar"]
