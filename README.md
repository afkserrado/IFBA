# README – Status Atual do Projeto

## Resumo

Até o momento foi realizada a implementação da infraestrutura base da arquitetura de microsserviços, contemplando autenticação compartilhada, documentação das APIs e integração entre os serviços.

O projeto encontra-se funcional estruturalmente, restando principalmente testes integrados, melhorias de arquitetura e implementação das funcionalidades pendentes.

---

# Funcionalidades Implementadas

## ✅ security-common

Foi criado o módulo **security-common**, responsável por centralizar toda a infraestrutura de autenticação da aplicação.

### Objetivos

Antes da implementação, cada microsserviço precisaria possuir sua própria configuração de segurança, ocasionando:

* duplicação de código;
* dificuldade de manutenção;
* maior possibilidade de inconsistências entre os serviços;
* necessidade de atualizar a configuração em diversos locais.

O módulo **security-common** resolve esse problema centralizando toda a configuração de segurança em uma única biblioteca reutilizável.

### Componentes disponibilizados

* Configuração padrão do Spring Security;
* Filtro JWT responsável por autenticar requisições;
* Serviço responsável pela validação dos tokens JWT;
* Classe de propriedades para configuração das rotas públicas;
* Configuração automática através de AutoConfiguration;
* PasswordEncoder compartilhado.

Cada microsserviço apenas adiciona a dependência do módulo e define em seu `application.properties`:

* chave JWT;
* rotas públicas específicas.

Toda a configuração restante passa a ser carregada automaticamente.

---

## ✅ Integração dos Microsserviços com o security-common

Os microsserviços passaram a compartilhar a mesma infraestrutura de autenticação.

Foram configurados para utilizar:

* autenticação JWT;
* filtro de autenticação compartilhado;
* configuração automática do Spring Security;
* propriedades específicas de rotas públicas.

Com isso, todos utilizam exatamente a mesma política de autenticação.

---

## ✅ Documentação Swagger

Foi adicionada documentação Swagger/OpenAPI em todos os controllers dos microsserviços.

A documentação inclui:

* descrição dos endpoints;
* parâmetros;
* respostas de sucesso;
* organização das APIs para facilitar testes.

Isso permite que toda a API possa ser explorada visualmente pelo Swagger UI.

---

# Estrutura implementada

* ✅ acervo-ms
* ✅ emprestimos-ms
* ✅ usuarios-ms
* ✅ gateway-ms
* ✅ security-common

---

# Pendências

## Testes

* [X] Validar todo o fluxo de autenticação utilizando o Swagger.
* [ ] Testar comunicação entre todos os microsserviços.
* [X] Validar funcionamento das chamadas Feign.
* [ ] Testar fluxo completo através do Gateway.

---

## Swagger

* [ ] Documentar todas as respostas de erro (400, 401, 403, 404, 409, 500...) de todos os endpoints.

---

## Arquitetura

* [ ] Implementar Service Discovery.
* [ ] Configurar balanceamento de carga utilizando o Service Discovery.
* [ ] Alterar configuração de CORS para permitir requisições provenientes do Frontend.

---

## Serviço de Notificações

* [ ] Implementar microsserviço de notificações.
* [ ] Integrar o serviço às necessidades dos demais microsserviços.

---

# Pendências do emprestimos-ms (Andersson)

* [ ] Corrigir o método `registrarDevolucao` do `EmprestimoService`, ajustando corretamente as regras de negócio referentes ao fluxo de devolução e atualização de status.

* [ ] Corrigir o método `listarTodos`, substituindo:

```java
collect(Collectors.toList())
```

por

```java
.toList()
```

* [ ] Ajustar `EmprestimoRequest`, removendo o atributo `dataPrevistaDevolucao`.

* [ ] Ajustar o `EmprestimoMapper`, removendo regras de negócio referentes às datas.

* [ ] Preferencialmente mover para o `EmprestimoService` as regras:

* dataEmprestimo = hoje;

* dataPrevistaDevolucao = hoje + 7 dias;

* dataDevolucao = null;

* status = ATIVO;

* valorMulta = 0;

* multaPaga = false.

Concentrando todas as regras de negócio na camada de serviço.

* [ ] Implementar autenticação/autorização JWT no `emprestimos-ms`.

* [ ] Implementar publicação de eventos RabbitMQ para:

  * criação de empréstimo;
  * devolução registrada.

---

# Organização das Pendências

Sempre que algum integrante iniciar uma atividade, atualizar este README indicando o responsável.

Exemplo:

* [ ] Documentar erros no Swagger *(Vinicius - Implementando)*

ou

* [ ] Implementar Service Discovery *(Andersson - Implementando)*

Dessa forma toda a equipe consegue acompanhar quem está responsável por cada tarefa, evitando retrabalho e mantendo o andamento do projeto organizado.

---

# Checklist Geral

## Estrutura

* [x] acervo-ms
* [x] emprestimos-ms
* [x] usuarios-ms
* [x] gateway-ms
* [x] security-common

## Infraestrutura

* [x] Implementação do security-common
* [x] Configuração compartilhada de autenticação JWT
* [x] Integração dos microsserviços com o security-common
* [x] Documentação Swagger dos controllers

## Pendências

* [ ] Testar fluxo completo via Swagger
* [ ] Testar comunicação entre microsserviços
* [ ] Documentar erros dos endpoints no Swagger
* [ ] Implementar Service Discovery
* [ ] Implementar serviço de notificações
* [ ] Alterar configuração de CORS para o Frontend
* [ ] Corrigir pendências do emprestimos-ms
* [ ] Implementar publicação de eventos RabbitMQ
* [x] Validar autenticação completa em todos os microsserviços

---

**Última atualização:** 20/Julho/2026.
