# README – Status Atual do Projeto

## Resumo

Até o momento foi realizada a implementação da infraestrutura base da arquitetura de microsserviços, contemplando autenticação compartilhada, documentação das APIs, comunicação síncrona entre serviços utilizando OpenFeign e integração entre os microsserviços.

O projeto encontra-se funcional estruturalmente, com os principais fluxos de negócio implementados e testados.

Todos os microsserviços foram validados individualmente, incluindo as comunicações síncronas entre serviços através do Feign.

---

# Checklist Geral

## Estrutura

* [x] acervo-ms
* [x] emprestimos-ms
* [x] usuarios-ms
* [x] gateway-ms
* [x] security-common

---

## Infraestrutura

* [x] Implementação do security-common
* [x] Configuração compartilhada de autenticação JWT
* [x] Integração dos microsserviços com o security-common
* [x] Documentação Swagger dos controllers
* [x] Comunicação síncrona entre microsserviços utilizando OpenFeign

---

## Funcionalidades

* [x] Cadastro de empréstimos
* [x] Devolução de livros
* [x] Cancelamento de empréstimos
* [x] Controle automático de datas
* [x] Comunicação entre microsserviços via Feign
* [x] Autenticação JWT completa
* [x] Cadastro de livros
* [x] Gerenciamento completo de estoque
* [x] Busca de livros por diferentes filtros
* [x] Gerenciamento de usuários

---

## Testes Realizados

Todos os microsserviços foram testados individualmente e os fluxos principais de negócio foram validados.

---

# usuario-ms

Testes realizados:

* [x] Autenticação de usuário
* [x] Registro de usuário
* [x] Restrição de criação de administrador por usuário comum
* [x] Busca de usuário por ID
* [x] Atualização de usuário
* [x] Busca de todos os usuários
* [x] Busca por email
* [x] Deleção por ID

**Status:**

Fluxo principal de gerenciamento de usuários validado.

---

# acervo-ms

Testes realizados:

* [x] Cadastro de livros
* [x] Listagem paginada de livros
* [x] Atualização de livros
* [x] Deleção de livros
* [x] Aumentar unidade do estoque
* [x] Diminuir unidade do estoque
* [x] Bloqueio de redução de estoque zerado
* [x] Verificação de disponibilidade
* [x] Busca por título
* [x] Busca por ISBN
* [x] Busca por autor

**Status:**

Fluxo completo do gerenciamento de acervo validado.

---

# emprestimos-ms

Testes realizados:

* [x] Cadastro de empréstimo
* [x] Listagem de empréstimos
* [x] Devolução de empréstimo
* [x] Cancelamento de empréstimo
* [x] Consulta de empréstimos por usuário
* [x] Consulta de empréstimo ativo por livro

**Status:**

Fluxo completo de empréstimos validado.

---

# Testes de Integração entre Microsserviços

As integrações síncronas utilizando OpenFeign foram testadas dentro dos fluxos reais do sistema.

## emprestimos-ms → usuarios-ms

Durante o cadastro de empréstimo:

* [x] Consulta existência do usuário
* [x] Validação do usuário antes do empréstimo

Resultado:

Comunicação funcionando corretamente.

---

## emprestimos-ms → acervo-ms

Durante o cadastro de empréstimo:

* [x] Consulta disponibilidade do livro
* [x] Validação de estoque
* [x] Redução automática do estoque

Resultado:

Comunicação funcionando corretamente.

---

# Script de Testes

## Autenticação

Antes de executar endpoints protegidos, deve ser realizado login utilizando um usuário administrador.

Exemplo:

```
POST /auth/login
```

Body:

```json
{
  "email": "admin@email.com",
  "senha": "senha"
}
```

Resposta esperada:

```json
{
  "token": "jwt-token-gerado"
}
```

Copie o token retornado.

---

## Utilização do Token

O token deve ser enviado nas chamadas protegidas.

### Postman / Insomnia

Adicionar no Header:

```
Authorization: Bearer SEU_TOKEN_AQUI
```

Exemplo:

```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

### Swagger

1. Acessar o botão:

```
Authorize 🔒
```

2. Informar:

```
Bearer SEU_TOKEN_AQUI
```

3. Confirmar.

Após isso, todas as chamadas protegidas serão realizadas utilizando o token.

---

# Pendências

* [ ] Testar fluxo completo via Gateway
* [x] Testar comunicação entre microsserviços via Feign
* [ ] Revisar autorizações e permissões do sistema
* [ ] Garantir funcionalidades do usuário comum autenticado
* [ ] Documentar erros dos endpoints no Swagger
* [ ] Implementar serviço de notificações
* [ ] Implementar publicação de eventos RabbitMQ

---

# Detalhamento das Pendências

## Revisão de permissões

Ainda é necessário revisar todas as regras de autorização por perfil.

Pendências:

* [X] Validar endpoints disponíveis para ADMIN
* [ ] Validar endpoints disponíveis para usuário comum
* [X] Garantir que operações administrativas estejam protegidas
* [ ] Revisar permissões aplicadas nos microsserviços

---

## Fluxo de usuário comum

Ainda falta validar completamente o comportamento de um usuário comum autenticado.

Pendências:

* [X] Criar usuário comum
* [X] Autenticar usuário comum
* [ ] Validar acesso aos endpoints permitidos
* [X] Confirmar bloqueio de operações administrativas

---

# Próximos Passos

- Finalizar revisão das regras de autorização;
- Realizar testes completos por perfil de usuário;
- Testar todos os fluxos através do gateway;
- Documentar respostas de erro no Swagger;
- Implementar funcionalidades futuras como notificações e eventos assíncronos.