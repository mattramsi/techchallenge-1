# Usar a imagem base do OpenJDK
FROM openjdk:17

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR do seu aplicativo para o contêiner
COPY target/techchallengeapi.jar techchallengeapi.jar

# Copie o script de migração do Flyway
COPY ./src/main/resources/db/migration /flyway/sql

# Baixe o wait-for-it.sh e conceda permissões de execução
RUN apt-get update && apt-get install -y curl && \
    curl -o /wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /wait-for-it.sh

# Instale o cliente MySQL para que o Flyway possa se conectar ao banco de dados
RUN apt-get install -y default-mysql-client

# Execute o Flyway para aplicar as migrações antes de iniciar a aplicação
ENTRYPOINT ["/wait-for-it.sh", "mysqldb:3306", "--", "flyway", "migrate", "-url=jdbc:mysql://mysqldb:3306/techchallenge", "-user=dbuser", "-password=1234", "-locations=filesystem:/flyway/sql"]

# Comando para iniciar a aplicação Java após a migração
CMD ["java", "-jar", "/app/techchallengeapi.jar"]
