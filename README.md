# Golden Raspberry Awards API

## Introdução

Bem-vindo ao projeto da API RESTful para acesso aos indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards. Este projeto visa fornecer uma solução para leitura desses dados de forma eficiente e organizada.

## Tecnologias Utilizadas

- Java 17
- Maven 3.9.5
- Spring Boot 3.2.2
- H2 Database (em memória)
- springdoc-openapi v2.4.0

## Instruções de Uso

1. Clonar o Repositório: Para obter uma cópia do projeto, clone este repositório utilizando o comando:

```bash
git clone https://github.com/claudioivp/golden-raspberry-awards-api.git
```

2. Executar o Projeto: Navegue até o diretório raiz do projeto e execute-o utilizando Maven:

```bash
mvn spring-boot:run
```

3. Acessar a API: Após a inicialização bem-sucedida, a API estará disponível em:

[http://localhost:8080/](http://localhost:8080/)

4. Documentação da API: A documentação da API está disponível através do Swagger, acessível em:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

5. Os testes de integração estão implementados para garantir a correta funcionalidade da API. Eles podem ser executados utilizando Maven:

```bash
mvn test
```

## Funcionalidades

### Leitura do Arquivo CSV

A API conta com uma funcionalidade dedicada à leitura do arquivo CSV dos filmes, o qual é armazenado no diretório **`src\main\resources\movielist.csv`**. Ao iniciar a aplicação, um CommandLineRunner do Spring é acionado, conforme configurado na classe CommandLineRunnerForProcessCSVConfig. Esta classe, localizada em **`src\main\java\com\texoit\goldenraspberryawardsapi\config\csv\CommandLineRunnerForProcessCSVConfig.java`**, é responsável por realizar o processamento do CSV. É importante ressaltar que o nome do arquivo CSV pode ser especificado nesta configuração. O processamento do CSV inclui a leitura dos dados e a inserção deles na base de dados em memória. Este procedimento é crucial para que a API possa fornecer informações precisas e atualizadas sobre os indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

### Endpoint para Obter Produtor com Maior e Menor Intervalo entre Prêmios

Este endpoint retorna os produtores que obtiveram o maior e o menor intervalo entre dois prêmios consecutivos.

- Resposta de Exemplo:

```json
{
   "min":[
      {
         "producer":"Producer 1",
         "interval":1,
         "previousWin":2008,
         "followingWin":2009
      },
      {
         "producer":"Producer 2",
         "interval":1,
         "previousWin":2018,
         "followingWin":2019
      }
   ],
   "max":[
      {
         "producer":"Producer 1",
         "interval":99,
         "previousWin":1900,
         "followingWin":1999
      },
      {
         "producer":"Producer 2",
         "interval":99,
         "previousWin":2000,
         "followingWin":2099
      }
   ]
}
```

## Licença

[MIT](https://choosealicense.com/licenses/mit/)

## Considerações Finais

Se encontrar algum problema ou tiver alguma dúvida, por favor, abra uma issue neste repositório. Obrigado por utilizar a API do Golden Raspberry Awards!