# ==========================================
# Estágio 1: Build (Compilação)
# ==========================================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia apenas o pom.xml primeiro
COPY pom.xml .

# Baixa as dependências (Isso cria uma camada de cache no Docker, 
# acelerando builds futuros se o pom.xml não for alterado)
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila o projeto e gera o arquivo .jar (ignorando testes para agilizar o build)
RUN mvn clean package -DskipTests

# ==========================================
# Estágio 2: Runtime (Execução)
# ==========================================
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho na imagem final
WORKDIR /app

# Copia apenas o arquivo .jar gerado no Estágio 1
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta que o acervo-ms utiliza
EXPOSE 8082

# Comando executado quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]