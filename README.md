# Firula8
Um cliente de acesso a plataforma Dribbble

## Funcionalidades

### Autenticação

Autorização do usuário para utilizar os serviços (é necessário logar)

Serviço:
```
GET https://dribbble.com/oauth/authorize
```

Adiquirir código de acesso:
```
POST https://dribbble.com/oauth/token
```

As informações do token são armazenadas em sessão.

### Listagem de shots
Lista os 30 primeiros shots do usuário autenticado

Serviço:
```
GET /user/shots?page=1&per_page=30"
```

### Shot
Apresenta o shot selecionado na listagem

Serviço:
```
GET /shots/:id
```

## Mudanças de versão de API
Infelizmente a versão 1 (v1) da api está depreciada, com isso houveram algumas alterações com essa mudança

### Autenticação
Na versão anterior não havia necessidade do usuário autenticar, porém nessa é necessário o usuário possuir login e senha, inclusive ser uma autenticação web.

### Campos removidos
Os campos "created_at", "views_counts" e "comments_counts", também foram removidos, tanto da listagem, quanto do serviço de pegar único shot. O campo "created_at" foi substituido por "published_at"

### Listagem por usuário
A listagem de shots como era antigamente:
```
GET /shots
```

Ela permitia mostrar a listagem de todos os shots, porém hoje, segundo a documentação, lista apenas os shots do usuário autenticado
```
GET /user/shots
```




