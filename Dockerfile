# Usa una imagen base de OpenJDK 21
FROM eclipse-temurin:21

# Copia el archivo JAR de tu aplicación Spring Boot
COPY target/ventas-0.0.1-SNAPSHOT.jar ventas.jar

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "/ventas.jar"]
