FROM ubuntu:22.04 AS build

RUN apt-get update && apt-get install -y \
    maven \
    openjdk-21-jdk \
    git \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app/ApiControleFinanceiro

# Clone o repositório
RUN git clone https://github.com/FelipeJaber/ApiControleFinanceiro.git .

# Verifique se o pom.xml existe
RUN ls

# Mude para o diretório correto onde o pom.xml está
WORKDIR /app/ApiControleFinanceiro

# Compile o projeto
RUN mvn clean package

FROM openjdk:21-jdk

WORKDIR /ApiControleFinanceiro

COPY --from=build /app/ApiControleFinanceiro/target/ApiControleFinanceiro-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=h2
ENV JWT_SECRET=PlaceholderKey
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://pgpool:5430/ApiControleFinanceiro
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=password

CMD ["sh", "-c", "java -jar app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE} --api.security.token.secret=${JWT_SECRET} --spring.datasource.url=${SPRING_DATASOURCE_URL} --spring.datasource.username=${SPRING_DATASOURCE_USERNAME} --spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}"]
