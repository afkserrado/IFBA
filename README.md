# Sistema de Gerenciamento de Biblioteca

Este trabalho foi desenvolvido pelos alunos Anderson Serrado, Anna Luisa e Vinícius Santos da disciplina **INF012 Programação Web**, turma 2026.1, do Instituto Federal da Bahia (IFBA).

Orientação: Prof. Dr. Manoel Carvalho Marques Neto.

## 1. Descrição do projeto

Sistema para gerenciamento de acervo, empréstimos e usuários de uma biblioteca, desenvolvido com uma arquitetura distribuída orientada a microsserviços. A solução disponibiliza uma interface web intuitiva para os usuários e administradores, permitindo o gerenciamento de catálogo de livros, movimentações de empréstimos e devoluções, controle de acessos por perfil e notificações assíncronas por e-mail enviadas automaticamente a cada evento relevante no sistema.

---

## 2. Arquitetura utilizada

A aplicação foi desenvolvida seguindo os padrões de **Arquitetura Baseada em Microsserviços**:

* **Responsabilidade Única**: cada microsserviço gerencia exclusivamente o seu próprio domínio de negócio.

	- **`acervo-ms`**: gerencia o catálogo e acervo de livros da biblioteca. É responsável pelo CRUD de obras, controle de estoque (quantidade total e disponível) e por publicar eventos via RabbitMQ sempre que um livro é cadastrado ou removido.

	- **`email-ms`**: serviço técnico dedicado estritamente ao envio de mensagens de e-mail através do protocolo SMTP (utilizando `JavaMailSender`).

	- **`emprestimos-ms`**: controla todo o ciclo de vida dos empréstimos (registro de novos empréstimos, devoluções, cancelamentos, cobrança de multas e rotina diária de atrasos). Integra-se com o acervo para atualizar estoque e com o módulo de usuários para validar a situação do usuário.

	- **`gateway-ms`**: funciona como a porta de entrada única da aplicação (porta `8080`). É responsável por receber o tráfego do frontend, gerenciar políticas de CORS e rotear as requisições HTTP para seus respectivos microsserviços (`usuarios-ms`, `acervo-ms` e `emprestimos-ms`).

	- **`notificacoes-ms`**: consumidor de mensagens do RabbitMQ. Ele escuta eventos de negócio emitidos por outros módulos (como criação/remoção de livros ou usuários) e consome o `email-ms` via OpenFeign para disparar os e-mails informativos.

	- **`usuarios-ms`**: gerencia o cadastro de usuários e administradores, além de ser a autoridade autenticadora da aplicação (responsável pelo login e emissão dos tokens JWT). Também valida se um usuário pode ser excluído verificando pendências com o módulo de empréstimos.

* **Isolamento de Dados**: utilização de uma instância do **PostgreSQL** com **schemas independentes** para cada microsserviço (`usuarios-ms`, `acervo-ms` e `emprestimos-ms`).

* **API Gateway (`gateway-ms`)**: Atua como o ponto de entrada único (porta `8080`), realizando o roteamento de requisições HTTP e gerenciamento de CORS.

* **Comunicação Síncrona**: feita via **OpenFeign** para validações diretas de regras de negócio entre serviços (ex: verificar status do usuário ou disponibilidade do acervo).

* **Comunicação Assíncrona**: baseada em eventos orientados a mensageria com **RabbitMQ** (Topic Exchanges), permitindo desacoplamento na emissão de eventos cadastrais e notificações de e-mail.

* **Segurança Compartilhada (`security-common`)**: módulo contendo autorização centralizada via JWT (HMAC-SHA512) e propagação automática de cabeçalhos de autenticação via *Feign Interceptor*.

### Componentes e portas

```
[ Frontend React ] (Porta 5173 / Vite)
        |
        v
[ Gateway-MS ] (Porta 8080)
__________________________|__________________________
           |              |              |
           v              v              v
    [usuarios-ms]   [acervo-ms]   [emprestimos-ms]
    (Porta 8081)    (Porta 8082)    (Porta 8083)
           |              |
           |--(RabbitMQ)--|
                  |
                  v
          [notificacoes-ms]  --- (OpenFeign) --->  [email-ms]
            (Porta 8084)                          (Porta 8085)
```

---

## 3. Tecnologias empregadas

### Backend

* **Linguagem & Framework**: Java 21, Spring Boot 3.x / 4.x
* **Persistência**: Spring Data JPA, Hibernate, PostgreSQL 16
* **Segurança**: Spring Security, JJWT (JSON Web Token)
* **Comunicação Inter-serviços**: Spring Cloud OpenFeign, Spring AMQP / RabbitMQ
* **Documentação de API**: Springdoc OpenAPI / Swagger UI
* **Utilitários**: Bean Validation, Spring Dotenv, Maven

### Frontend

* **Core**: ReactJS, Vite
* **Roteamento**: React Router (rotas protegidas por perfil)
* **Comunicação HTTP**: Axios
* **UI & Estilização**: Bootstrap, React Hooks, Context API (`AuthContext`)

### Infraestrutura

* **Containers**: Docker, Docker Compose
* **Mensageria**: RabbitMQ (AMQP 5672, Management 15672)

---

## 4. Instruções de execução

> **Observação:** As instruções consideram o projeto localizado no diretório `/home/<seu-usuario>/Downloads/TRABALHO-PWEB`. Substitua `<seu-usuario>` pelo nome de usuário no seu sistema operacional.

### Pré-requisitos

* Java 21 JDK instalado
* Apache Maven 3.9+ instalado
* Node.js 18+ e NPM instalados
* Docker e Docker Compose instalados e em execução

---

### Passo 1: Subir a infraestrutura (PostgreSQL e RabbitMQ)

Acesse a pasta `infra` e inicie os containers do banco de dados e servidor de mensageria:

- **Linux:**

```bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/infra
docker compose up -d
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\infra
docker compose up -d
```

Para verificar se os contêineres estão em execução:

```Bash
docker ps
```

Para verificar os schemas criados no banco:

- **Linux e Windows:**

```Bash
docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c '\dn'
```

### Passo 2: Criar um arquivo `.env` na raíz do projeto

Na pasta `TRABALHO-PWEB`, crie um arquivo chamado `.env` com o conteúdo abaixo:

```Properties
# ============================
# Configuração do envio de e-mails
# ============================

# E-mail utilizado pelo microsserviço email-ms para envio das mensagens (remetente)
# Insira o seu e-mail
EMAIL_USERNAME=email@gmail.com

# Senha de aplicativo do provedor de e-mail (não utilizar a senha comum da conta)
# Insira a sua senha
EMAIL_PASSWORD=sua_senha_de_app

# E-mail que receberá notificações automáticas do sistema (destinatário do RabbitMQ)
# Insira outro e-mail (pode ser o e-mail anterior também)
EMAIL_NOTIFICACAO_DESTINO=admin@seudominio.com

# ============================
# Usuário administrador inicial
# ============================

# Credenciais utilizadas para criação do administrador padrão da aplicação
# Não é necessário alterar essa parte
ADMIN_NAME=FirstAdmin
ADMIN_EMAIL=admin@biblioteca.com
ADMIN_PASSWORD=admin123

# CPF do administrador inicial
ADMIN_CPF=00000000000
```

### Passo 3: Compilar e instalar o Módulo Comum de Segurança

O módulo `security-common` deve ser compilado e instalado no seu repositório Maven local antes de iniciar os microsserviços:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/security-common
mvn clean install
```

_(Caso não possua o Maven instalado globalmente, substitua `mvn` por `./mvnw`)_

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\security-common
mvn clean install
```

_(Caso não possua o Maven instalado globalmente, substitua `mvn` por `mvnw.cmd`)_

### Passo 4: Inicializar os Microsserviços Backend

Abra terminais/janelas independentes para cada microsserviço e execute-os **estritamente na ordem abaixo**:

1. **Terminal 1 - Gateway (`gateway-ms`)**:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/gateway-ms
./mvnw clean spring-boot:run
```

_(Caso a permissão de execução seja negada no Linux, faça: `chmod +x ./mvnw`)_

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\gateway-ms
mvnw.cmd clean spring-boot:run
```

2. **Terminal 2 - Usuários (`usuarios-ms`)**:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/usuarios-ms
./mvnw clean spring-boot:run
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\usuarios-ms
mvnw.cmd clean spring-boot:run
```

2. **Terminal 3 - Acervo (`acervo-ms`)**:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/acervo-ms
./mvnw clean spring-boot:run
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\acervo-ms
mvnw.cmd clean spring-boot:run
```

2. **Terminal 4 - Empréstimos (`emprestimos-ms`)**:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/emprestimos-ms
./mvnw clean spring-boot:run
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\emprestimos-ms
mvnw.cmd clean spring-boot:run
```

2. **Terminal 5 - E-mail (`email-ms`)**:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/email-ms
./mvnw clean spring-boot:run
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\email-ms
mvnw.cmd clean spring-boot:run
```

2. **Terminal 6 - Notificações (`notificacoes-ms`)**:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/backend/notificacoes-ms
./mvnw clean spring-boot:run
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\backend\notificacoes-ms
mvnw.cmd clean spring-boot:run
```

#### Execução Alternativa por Script

- **Linux (Shell Script):**

Você pode utilizar o script abaixo para executá-los em segundo plano dentro da pasta `backend` ou de alguma pasta pai:

```Bash
#!/bin/bash

echo "======================================================="
echo " INICIALIZAÇÃO DOS MICROSSERVIÇOS SPRING BOOT"
echo "======================================================="

# Procura a pasta backend a partir do diretório atual.

if [ "$(basename "$PWD")" = "backend" ]; then
    BACKEND_DIR="$PWD"

elif [ "$(basename "$PWD")" = "TRABALHO-PWEB" ] && [ -d "backend" ]; then
    BACKEND_DIR="$PWD/backend"

elif [ -d "TRABALHO-PWEB/backend" ]; then
    BACKEND_DIR="$PWD/TRABALHO-PWEB/backend"

else
    echo
    echo "Erro: não foi possível localizar TRABALHO-PWEB/backend"
    echo
    read -p "Pressione ENTER para fechar..."
    exit 1
fi

cd "$BACKEND_DIR" || exit

echo
echo "Executando na pasta:"
pwd
echo

# ======================================================
# Compila biblioteca compartilhada
# ======================================================

if [ -d "security-common" ]; then

    echo "Compilando security-common..."

    cd security-common || exit

    bash mvnw clean install compile

    if [ $? -ne 0 ]; then
        echo
        echo "Erro ao compilar security-common."
        read -p "Pressione ENTER para fechar..."
        exit 1
    fi

    echo "security-common compilado com sucesso."

    cd "$BACKEND_DIR" || exit

else

    echo
    echo "Aviso: security-common não encontrado."
    echo

fi

# ======================================================
# Encerra microsserviços antigos
# ======================================================

PIDS=$(lsof -t -i:8080 -i:8081 -i:8082 -i:8083 -i:8084 -i:8085)

if [ -n "$PIDS" ]; then

    kill $PIDS
    echo "Microsserviços anteriores encerrados."

else

    echo "Nenhum microsserviço em execução encontrado."

fi

# ======================================================
# Inicialização dos microsserviços
# ======================================================

SERVICOS=(
    "gateway-ms"
    "usuarios-ms"
    "acervo-ms"
    "emprestimos-ms"
    "email-ms"
    "notificacoes-ms"
)

for servico in "${SERVICOS[@]}"; do

    CAMINHO="$BACKEND_DIR/$servico"

    if [ -d "$CAMINHO" ]; then

        echo "Iniciando $servico..."

        (
            cd "$CAMINHO" || exit

            bash mvnw clean spring-boot:run \
            > "$HOME/${servico}.log" 2>&1

        ) &

        sleep 3

    else

        echo "Aviso: $servico não encontrado."

    fi

done

echo
echo "Microsserviços iniciados."
echo
echo "Logs disponíveis em:"
echo "$HOME/<nome-do-servico>.log"
echo

read -p "Pressione ENTER para fechar..."
```

- **No Windows (Batch Script):**

Salve o conteúdo abaixo em um arquivo chamado `iniciar_ms.bat` dentro da pasta `backend` e execute-o para abrir cada microsserviço em uma nova janela automaticamente:

```PowerShell
@echo off
chcp 65001 > nul
echo =======================================================
echo  INICIALIZAÇÃO DOS MICROSSERVIÇOS SPRING BOOT (WINDOWS)
echo =======================================================

set "BACKEND_DIR=%~dp0"
cd /d "%BACKEND_DIR%"

if exist "security-common" (
    echo Compilando security-common...
    cd security-common
    call mvnw.cmd clean install compile
    if errorlevel 1 (
        echo.
        echo Erro ao compilar security-common.
        pause
        exit /b 1
    )
    echo security-common compilado com sucesso.
    cd /d "%BACKEND_DIR%"
) else (
    echo.
    echo Aviso: security-common não encontrado.
    echo.
)

echo.
echo Iniciando microsserviços em novas janelas...
echo.

start "Gateway MS" cmd /k "cd /d %BACKEND_DIR%\gateway-ms && mvnw.cmd clean spring-boot:run"
timeout /t 3 > nul

start "Usuarios MS" cmd /k "cd /d %BACKEND_DIR%\usuarios-ms && mvnw.cmd clean spring-boot:run"
timeout /t 3 > nul

start "Acervo MS" cmd /k "cd /d %BACKEND_DIR%\acervo-ms && mvnw.cmd clean spring-boot:run"
timeout /t 3 > nul

start "Emprestimos MS" cmd /k "cd /d %BACKEND_DIR%\emprestimos-ms && mvnw.cmd clean spring-boot:run"
timeout /t 3 > nul

start "Email MS" cmd /k "cd /d %BACKEND_DIR%\email-ms && mvnw.cmd clean spring-boot:run"
timeout /t 3 > nul

start "Notificacoes MS" cmd /k "cd /d %BACKEND_DIR%\notificacoes-ms && mvnw.cmd clean spring-boot:run"

echo Microsserviços inicializados.
pause
```

### Passo 5: Executar a Aplicação Frontend

Em um novo terminal, acesse a pasta do frontend, instale as dependências e inicie o servidor de desenvolvimento:

- **Linux:**

```Bash
cd /home/<seu-usuario>/Downloads/TRABALHO-PWEB/frontend
npm install
npm run dev
```

- **Windows:**

```PowerShell
cd C:\Users\<seu-usuario>\Downloads\TRABALHO-PWEB\frontend
npm install
npm run dev
```

Acesse a aplicação no navegador através do endereço: `http://localhost:5173`.

Você pode fazer login no usuário `FirstAdmin`:

- **e-mail:** admin@biblioteca.com
- **senha:** admin123

## 5. Divisão das responsabilidades da equipe

**Anderson**
- Implementação da infraestrutura (docker compose com PostgreSQL e RabbitMQ);
- Modelagem de template para o backend;
- Implementação dos microsserviços `acervo-ms`, `email-ms` e `notificacoes-ms` (comunicação assíncrona com RabbitMQ);
- Criação das coleções de testes;
- Testes de requisição;
- README.

**Anna Luisa**
- Implementação do microsserviço `emprestimos-ms`;
- Implementação do frontend.

**Vinícius Santos**
- Implementação do `gateway-ms`;
- Implementação do `security-common` (Spring Security com JWT);
- Implementação do `usuarios-ms`;
- Integração frontend x backend;
- Testes de requisição.