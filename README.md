# Projeto Desafio TOTVS - Rodrigo Zietlow

## API

Feito com Java Spring, gerado via **Spring Initializr**, localizado em `./api/`. Docs em `./api/docs`, geradas com Javadoc. Para subir, executar com maven:

```
$ mvn spring-boot:run
```

Foram criados dois `RestController`, um para cada entidade (`Phone` e `Client`), implementados os verbos HTTP e também um método para realizar a validação das regras externas em cada (Para o `Client` a regra do nome único e para o `Phone` a regra do número não existir para outro cliente).

A persistência foi realizada com **H2** em memória, pré-carregado ([`MockDB.java`](./api/src/main/java/com/rodrigozietlow/desafiototvs/api/MockDb.java) para mais detalhes).

Para rodar os testes unitários, pode-se executar:

```
$ mvn test
```

## Front-end

Feito com **Angular 17**, localizado em `./frontend/`. Pode ser executado via:

```
$ npm install
$ ng serve
```

Após concluído, a aplicação estará disponível em `http://localhost:4200`.

Utilizei **Angular Material** para os estilos e componentes.
