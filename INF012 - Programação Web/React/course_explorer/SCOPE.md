# Exercício de Revisão – React JS

## Sistema de Consulta de Cursos Online

Desenvolva uma aplicação React JS chamada **Course Explorer** para consulta de cursos online utilizando uma API fictícia.

O objetivo do exercício é revisar:

- Class Based Components
- Function Based Components
- Hooks (`useState`, `useEffect`)
- Axios
- React Router DOM
- CSS Básico
- Comunicação com API REST

---

# 1. Página Inicial (`/`)

## Requisitos

- Título da aplicação
- Menu de navegação
- Listagem resumida dos cursos obtidos via requisição HTTP GET
- Cada curso deve possuir:
  - ID
  - Nome
  - Categoria
  - Botão/link **“Detalhar”**

---

# 2. Página de Detalhes (`/courses/:id`)

## Requisitos

- Navegar para rota dinâmica
- Fazer nova requisição HTTP GET utilizando o `id`
- Exibir detalhes completos do curso

---

# API Fictícia

## Endpoint para Listagem

### Requisição

```http
GET https://fake-api.dev/courses
```

### Resposta esperada

```json
[
  {
    "id": 1,
    "name": "React Fundamentals",
    "category": "Frontend"
  },
  {
    "id": 2,
    "name": "Spring Boot API",
    "category": "Backend"
  },
  {
    "id": 3,
    "name": "Docker Essentials",
    "category": "DevOps"
  }
]
```

---

## Endpoint para Detalhes

### Requisição

```http
GET https://fake-api.dev/courses/{id}
```

### Exemplo

```http
GET https://fake-api.dev/courses/1
```

### Resposta esperada

```json
{
  "id": 1,
  "name": "React Fundamentals",
  "category": "Frontend",
  "instructor": "João Silva",
  "duration": "20 horas",
  "students": 1200,
  "description": "Curso introdutório de React JS."
}
```

---

# Parte 1 — Function Based Component

## Requisitos

- Criar componente funcional `CourseList`
- Utilizar `useState`
- Utilizar `useEffect`
- Fazer requisição HTTP usando Axios
- Exibir cursos em tela

---

# Parte 2 — Class Based Component

## Requisitos

- Criar componente baseado em classe `CourseDetails`
- Utilizar `state`
- Utilizar `componentDidMount`
- Fazer requisição HTTP usando Axios
- Buscar detalhes do curso pelo ID
- Exibir informações completas

---

# Parte 3 — React Router DOM

## Requisitos

- Configurar as rotas:
  - `/`
  - `/courses`
  - `/courses/:id`

---

# Parte 4 — CSS Básico

## Requisitos

- Estilização do menu
- Cards para cursos
- Botões
- Espaçamentos e margens
- Hover simples

---

# Observações

- Não utilizar bibliotecas de UI prontas
- O foco é React puro + CSS básico
- A API é fictícia; o aluno pode mockar os dados utilizando:
  - JSON Server
  - MirageJS
  - MockAPI
  - Dados simulados