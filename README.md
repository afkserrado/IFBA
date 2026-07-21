# README – Status Atual do Projeto

## Resumo

Até o momento foi realizada a implementação da infraestrutura base da arquitetura de microsserviços, contemplando autenticação compartilhada, documentação das APIs e integração entre os serviços.

O projeto encontra-se funcional estruturalmente, com os principais fluxos implementados, restando principalmente testes integrados, revisão das regras de autorização e implementação de funcionalidades complementares.

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

Com isso, todos utilizam a mesma política de autenticação.

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

# Funcionalidades Implementadas no emprestimos-ms

As principais regras de negócio do microsserviço de empréstimos foram concluídas.

## ✅ Devolução de livros

Corrigido o fluxo de devolução:

* empréstimo devolvido sempre recebe status `DEVOLVIDO`;
* atrasos geram apenas multa;
* status `ATRASADO` permanece apenas enquanto o livro não foi devolvido;
* estoque do acervo é atualizado após a devolução.

---

## ✅ Cancelamento de empréstimos

Implementado cancelamento de empréstimos:

* somente empréstimos com status `ATIVO` podem ser cancelados;
* exemplar retorna ao acervo;
* status alterado para `CANCELADO`;
* data de devolução não é preenchida;
* histórico do empréstimo é preservado.

---

## ✅ Controle automático das datas

Ajustado o fluxo de criação de empréstimos:

* remoção da data prevista de devolução enviada pelo cliente;
* prazo definido automaticamente pelo sistema;
* data prevista calculada internamente.

---

## ✅ Integração com outros microsserviços

O `emprestimos-ms` realiza chamadas síncronas para:

### usuarios-ms

Responsável por validar existência do usuário.

### acervo-ms

Responsável por:

* verificar disponibilidade de livros;
* reduzir estoque ao realizar empréstimo;
* aumentar estoque em devoluções e cancelamentos.

---

# Pendências

## Testes

* [X] Validar fluxo de autenticação utilizando Swagger.
* [ ] Testar comunicação completa entre todos os microsserviços.
* [ ] Testar fluxo completo utilizando Gateway.
* [ ] Criar testes integrados dos principais fluxos de negócio.

---

## Swagger

* [ ] Documentar todas as respostas de erro (400, 401, 403, 404, 409, 500...) de todos os endpoints.
* [ ] Revisar descrições dos endpoints para refletir completamente as regras atuais.

---

# Revisão das Autorizações do Sistema

## Pendência: validar permissões dos usuários

É necessário revisar todas as regras de autorização para garantir que o sistema seja funcional tanto para administradores quanto para usuários comuns.

### Pontos a validar:

* [ ] Verificar se usuários comuns autenticados possuem permissões suficientes para utilizar as funcionalidades esperadas.
* [ ] Revisar endpoints que atualmente exigem apenas autenticação, garantindo que não estejam bloqueando funcionalidades necessárias.
* [ ] Revisar endpoints que devem ser exclusivos para administradores.

---

## Fluxos esperados para usuário comum autenticado

Um usuário comum (`ROLE_USER`) deve conseguir:

### Acervo

* [ ] Visualizar catálogo de livros disponíveis.
* [ ] Consultar informações dos livros.
* [ ] Consultar disponibilidade dos exemplares.

---

### Empréstimos

* [ ] Visualizar seus próprios empréstimos.
* [ ] Consultar data prevista de devolução.
* [ ] Consultar histórico de empréstimos.
* [ ] Visualizar multas pendentes.
* [ ] Consultar situação dos seus empréstimos.

---

### Restrições esperadas

Usuários comuns não devem conseguir:

* [ ] Cadastrar novos livros.
* [ ] Alterar informações de livros.
* [ ] Remover usuários.
* [ ] Visualizar empréstimos de outros usuários.
* [ ] Executar operações administrativas.

---

# Serviço de Notificações

* [ ] Implementar microsserviço de notificações.
* [ ] Definir eventos que devem gerar notificações.
* [ ] Integrar notificações aos fluxos de empréstimo.

Possíveis eventos:

* criação de empréstimo;
* devolução registrada;
* atraso de devolução;
* multa gerada.

---

# Mensageria

## RabbitMQ

* [ ] Implementar publicação de eventos RabbitMQ.

Eventos previstos:

* criação de empréstimo;
* devolução registrada;
* cancelamento de empréstimo;
* geração de multa.

---

# Organização das Pendências

Sempre que algum integrante iniciar uma atividade, atualizar este README indicando o responsável.

Exemplo:

* [ ] Revisar permissões do sistema *(Vinicius - Implementando)*

ou

* [ ] Implementar notificações *(Andersson - Implementando)*

Dessa forma toda a equipe consegue acompanhar quem está responsável por cada tarefa, evitando retrabalho e mantendo o andamento do projeto organizado.

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

---

## Funcionalidades

* [x] Cadastro de empréstimos
* [x] Devolução de livros
* [x] Cancelamento de empréstimos
* [x] Controle automático de datas
* [x] Comunicação entre microsserviços via Feign
* [x] Autenticação JWT completa

---

## Pendências

* [ ] Testar fluxo completo via Gateway
* [ ] Testar comunicação completa entre microsserviços
* [ ] Revisar autorizações e permissões do sistema
* [ ] Garantir funcionalidades do usuário comum autenticado
* [ ] Documentar erros dos endpoints no Swagger
* [ ] Implementar serviço de notificações
* [ ] Implementar publicação de eventos RabbitMQ

---

**Última atualização:** 21/Julho/2026.