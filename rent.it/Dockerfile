# Define a imagem base
FROM openjdk:17-jdk-slim
# Copia o arquivo JAR do seu projeto para dentro do container
COPY target/rent.it-0.0.1-SNAPSHOT.jar rent.jar

# Define o comando de inicialização do seu projeto
CMD ["java", "-jar", "rent.jar"]