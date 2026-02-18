# Gerenciador de Produtos

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

Este projeto é uma aplicação de linha de comando desenvolvida em Java com Spring Boot, focada no gerenciamento de produtos, categorias e pedidos. A aplicação utiliza o Spring Data JPA para a persistência de dados em um banco PostgreSQL.

## Tecnologias Utilizadas
* **Java 17**
* **Spring Boot 4.0.2**
* **Spring Data JPA**
* **PostgreSQL**
* **Maven**

## Funcionalidades
O sistema oferece um menu interativo com as seguintes capacidades:
* **Cadastro**: Registro de produtos associados a categorias e pedidos.
* **Buscas e Filtros**:
    * Listagem total de produtos.
    * Busca de produtos por nome.
    * Filtro por categoria e faixas de preço.
* **Gestão de Pedidos**: Filtragem de pedidos com ou sem data, e buscas por intervalos de datas.



## Configuração
Para rodar a aplicação, é necessário configurar as seguintes variáveis de ambiente:
* `DB_URL`
* `DB_USERNAME`
* `DB_PASSWORD`

## Execução
Inicie a aplicação utilizando o Maven Wrapper:

```bash
./mvnw spring-boot:run
