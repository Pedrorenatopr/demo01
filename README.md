# API Demo 01

## 📌 Descrição do Projeto
Esta é uma API RESTful desenvolvida em Java utilizando o framework Spring Boot. O sistema foi projetado para gerenciar cadastros e consultas de usuários (incluindo entidades primárias e secundárias), implementando paginação de resultados, segurança de endpoints e documentação automatizada.

## 🚀 Tecnologias e Dependências
O projeto foi construído utilizando as seguintes tecnologias:
* **Java 17**
* **Spring Boot (v3.5.10)**
* **Maven** (Gerenciador de dependências)
* **MySQL** (Banco de dados relacional)
* **Flyway** (Migração e versionamento do banco de dados)
* **Spring Security & Autenticação JWT** (Controle de acesso)
* **SpringDoc / Swagger** (Documentação da API)
* **Lombok** (Otimização de código)

## 📋 Pré-requisitos
Antes de iniciar, certifique-se de ter instalado em sua máquina:
* [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/downloads/)
* Servidor MySQL rodando localmente (porta 3306)
* Git
* IDE de sua preferência (recomenda-se IntelliJ IDEA)

## ⚙️ Configuração do Ambiente
Para que a aplicação se conecte ao banco de dados corretamente, é necessário configurar as variáveis de ambiente em sua IDE ou no sistema operacional:

* `DB_USERNAME`: Seu usuário do MySQL (ex: root)
* `DB_PASSWORD`: Sua senha do MySQL

*(Nota: O Flyway criará e estruturará as tabelas automaticamente ao iniciar a aplicação, com base nos scripts de migração).*

## 🔧 Como executar o projeto localmente

1. Clone este repositório para a sua máquina:
   ```bash
   git clone [https://github.com/Pedrorenatopr/demo01.git](https://github.com/Pedrorenatopr/demo01.git)
Acesse a pasta raiz do projeto:

Bash
cd demo01
Compile e execute a aplicação utilizando o Maven wrapper:

Bash
./mvnw spring-boot:run
A API estará disponível e rodando localmente na porta padrão 8080.

📚 Documentação da API (Swagger)
Com a aplicação em execução, a interface gráfica para testes e visualização dos endpoints pode ser acessada através do navegador:
👉 http://localhost:8080/swagger-ui.html

Nesta interface, é possível testar as rotas de listagem (como GET /usuarios?page=0&size=5), cadastro (POST) e inserção de tokens de autorização (Bearer Token).

✒️ Autor
Pedro Renato - Desenvolvedor e Web Designer
