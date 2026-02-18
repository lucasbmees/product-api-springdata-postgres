# Gerenciador de Produtos

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
    * Busca de produtos por nome (ignora maiúsculas/minúsculas).
    * Filtro por categoria e faixas de preço (maior que, menor que, ou ordenação crescente/decrescente).
* **Gestão de Pedidos**: Filtragem de pedidos com ou sem data, e buscas por intervalos de datas.
* **Estatísticas**:
    * Média de preços dos produtos.
    * Preço máximo por categoria.
    * Listagem de categorias com mais de 2 produtos.

## Configuração
Para rodar a aplicação, é necessário configurar as seguintes variáveis de ambiente para conexão com o banco de dados:
* `DB_URL`
* `DB_USERNAME`
* `DB_PASSWORD`

## Execução
O projeto utiliza o Maven Wrapper. Para iniciar a aplicação, utilize o comando abaixo na raiz do projeto:

```bash
./mvnw spring-boot:run
