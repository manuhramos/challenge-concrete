# challenge-concrete

Desafio Concrete realizado por Emanuella Cavalcante baseado em https://github.com/concretesolutions/desafio-java

## Heroku

* Realizado deploy no Heroku
https://challenge-concrete-manu.herokuapp.com/

### Instalação
```sh
$ heroku login #inserir credenciais
$ cd ~/project-folder
$ heroku create challenge-concrete-manu #nome é opcional, o heroku gera um dinâmico se não for declarado
$ heroku keys:add #caso já possua uma id_rsa.pub
$ git push heroku master #se o projeto buildar corretamente ele informa a url após esse passo
```

## Endpoints
### POST /user
* Cria um novo usuário
* Exemplo entrada
```json
    {
        "name": "João da Silva",
        "email": "joao@silva.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "987654321",
                "ddd": "21"
            }
        ]
    }
```
* Exemplo retorno
```json
    {
    "id": "6C9971BAFA2E545086AE10611D21CE6CA7539F1106DF7BC61FB0F44E8D88E738",
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "1e32564234b0aa243fc9733c869588646febcca5",
    "phones": [
          {
            "number": "987654321",
            "ddd": "21"
        }
    ],
    "created": "Jun 13, 2018 10:58:03 PM",
    "modified": "Jun 13, 2018 10:58:03 PM",
    "last_login": "Jun 13, 2018 10:58:03 PM",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvQHNpbHZhLm9yZzoxNTI4OTQxNDg0NjQzIn0.RgZLDtmPXSdaZD4pTZcSUTYjPcT2zT9NAELMY12BEw-FGnqVjk9dW_2QcZdwWerotEk0pK2Z3-oC0N6_5QU0Tw"
    }
```

### GET /user/{id}
* Obtém o perfil do usuário autenticado via JWT

| Entrada | Tipo | Valor |
| ------ | ------ | ------ |
| id | path | 6C9971BAFA2E545086AE10611D21CE6CA7539F1106DF7BC61FB0F44E8D88E738 |
| token | header | eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvQHNpbHZhLm9yZzoxNTI4OTQxNDg0NjQzIn0.RgZLDtmPXSdaZD4pTZcSUTYjPcT2zT9NAELMY12BEw-FGnqVjk9dW_2QcZdwWerotEk0pK2Z3-oC0N6_5QU0Tw |

* Exemplo retorno
```json
    {
    "id": "6C9971BAFA2E545086AE10611D21CE6CA7539F1106DF7BC61FB0F44E8D88E738",
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "1e32564234b0aa243fc9733c869588646febcca5",
    "phones": [
          {
            "number": "987654321",
            "ddd": "21"
        }
    ],
    "created": "Jun 13, 2018 10:58:03 PM",
    "modified": "Jun 13, 2018 10:58:03 PM",
    "last_login": "Jun 13, 2018 10:58:03 PM",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvQHNpbHZhLm9yZzoxNTI4OTQxNDg0NjQzIn0.RgZLDtmPXSdaZD4pTZcSUTYjPcT2zT9NAELMY12BEw-FGnqVjk9dW_2QcZdwWerotEk0pK2Z3-oC0N6_5QU0Tw"
    }
```

### POST /login
* Realiza o login na API.
* Exemplo entrada
```json
    {
        "email": "joao@silva.org",
        "password": "hunter2"
    }
```
* Exemplo retorno
```json
    {
    "id": "6C9971BAFA2E545086AE10611D21CE6CA7539F1106DF7BC61FB0F44E8D88E738",
    "name": "João da Silva",
    "email": "joao@silva.org",
    "password": "1e32564234b0aa243fc9733c869588646febcca5",
    "phones": [
          {
            "number": "987654321",
            "ddd": "21"
        }
    ],
    "created": "Jun 13, 2018 10:58:03 PM",
    "modified": "Jun 13, 2018 10:58:03 PM",
    "last_login": "Jun 13, 2018 10:58:03 PM",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2FvQHNpbHZhLm9yZzoxNTI4OTQxNDg0NjQzIn0.RgZLDtmPXSdaZD4pTZcSUTYjPcT2zT9NAELMY12BEw-FGnqVjk9dW_2QcZdwWerotEk0pK2Z3-oC0N6_5QU0Tw"
    }
```
