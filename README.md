# book-store-manager-api
## Documentação Swagger
```
https://book-store-manager-curso.herokuapp.com/swagger-ui.html
```
## Demo para testes
```
https://book-store-manager-curso.herokuapp.com/api/v1/books
POST: {
    "name":"Investidor Inteligente",
    "pages":200,
    "chapters": 20,
    "publisherName": "Harper Collins",
    "isbn":"0-596-52068-9",
    "author": {
        "name": "Benjamin Graham",
        "age": 100
    }
}

GET: https://book-store-manager-curso.herokuapp.com/api/v1/books/{id}
```
## Requisitos:
```
Java 11 ou superior
Spring Boot 2.4
Maven 3
```
## Utilizar IDE Eclipse ou Intellij para executar projeto ou as linhas de comando abaixo

## Instalação de dependencias
```
mvn install
```
## Executar Projeto
```
mvn spring-boot:run
```

