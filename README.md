# API de Controle Financeiro

Esta API permite o gerenciamento de um sistema financeiro, onde os usuários podem cadastrar pessoas, grupos, metas, criar e editar lançamentos, e gerar relatórios detalhados. A API facilita o controle financeiro pessoal ou de grupos, com opções para gestão de metas e transações.

## Funcionalidades

- **Cadastro de Pessoas**: Crie e gerencie perfis de usuários.
- **Gerenciamento de Grupos**: Organize pessoas em grupos para controle financeiro compartilhado.
- **Definição de Metas**: Defina metas financeiras para usuários e grupos.
- **Lançamentos**: Crie, edite e exclua lançamentos financeiros (entradas e saídas).
- **Relatórios**: Geração de relatórios financeiros detalhados por período, grupo ou categoria.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **JPA/Hibernate**
- **H2 Database** (para desenvolvimento e testes)
- **PostgreSQL** (para produção)
- **Swagger** (para documentação da API)

## Como Executar a Aplicação

### Pré-requisitos

- Java 17+
- Maven 3.6+
- PostgreSQL (ou H2 para testes)
- Git

### Passos para executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/sua-api-financeiro.git
    cd sua-api-financeiro
    ```

2. Instale as dependências:
    ```bash
    mvn clean install
    ```

3. Configure o banco de dados no arquivo `application.properties` para apontar para o PostgreSQL:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/financeiro
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    ```

4. Execute a aplicação:
    ```bash
    mvn spring-boot:run
    ```

5. Acesse a documentação da API gerada automaticamente pelo Swagger em:
    ```
    http://localhost:8080/swagger-ui.html
    ```

## Estrutura da API

### Endpoints principais

- **/pessoas**
  - `GET /pessoas`: Lista todas as pessoas cadastradas.
  - `POST /pessoas`: Cadastra uma nova pessoa.
  - `PUT /pessoas/{id}`: Atualiza os dados de uma pessoa.
  - `DELETE /pessoas/{id}`: Remove uma pessoa do sistema.

- **/grupos**
  - `GET /grupos`: Lista todos os grupos.
  - `POST /grupos`: Cria um novo grupo.
  - `PUT /grupos/{id}`: Atualiza um grupo.
  - `DELETE /grupos/{id}`: Exclui um grupo.

- **/metas**
  - `GET /metas`: Lista todas as metas financeiras.
  - `POST /metas`: Cria uma nova meta.
  - `PUT /metas/{id}`: Atualiza uma meta.
  - `DELETE /metas/{id}`: Exclui uma meta.

- **/lancamentos**
  - `GET /lancamentos`: Lista todos os lançamentos financeiros.
  - `POST /lancamentos`: Cria um novo lançamento.
  - `PUT /lancamentos/{id}`: Atualiza um lançamento.
  - `DELETE /lancamentos/{id}`: Exclui um lançamento.

- **/relatorios**
  - `GET /relatorios`: Gera relatórios financeiros por período, grupo ou categoria.

## Testes

Para rodar os testes unitários, use o comando:
```bash
mvn test
