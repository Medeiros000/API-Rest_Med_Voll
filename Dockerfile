FROM openjdk:17-jdk-alpine

# Criar usuário não-root para segurança
RUN addgroup -g 1001 -S spring && \
    adduser -u 1001 -S spring -G spring

# Criar diretório da aplicação
RUN mkdir /app && chown spring:spring /app
WORKDIR /app

# Copiar o JAR da aplicação
COPY --chown=spring:spring *.jar /app/app.jar

# Mudar para usuário não-root
USER spring:spring

# Expor a porta
EXPOSE 8080

# Variáveis de ambiente padrão
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando para executar a aplicação
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
