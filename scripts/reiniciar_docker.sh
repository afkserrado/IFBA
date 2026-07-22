#!/bin/bash

# Entra na pasta da infraestrutura
cd "/mnt/Arquivos_D0P6LA/Codes/TRABALHO-PWEB/infra"

# Derruba containers, rede e volumes
docker compose down -v

# Sobe os serviços
docker compose up -d

echo "Aguardando PostgreSQL iniciar..."

until docker exec biblioteca_postgres pg_isready -U admin >/dev/null 2>&1
do
    sleep 2
done

echo "PostgreSQL iniciado!"

# Cria schemas
docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c \
"CREATE SCHEMA IF NOT EXISTS usuarios AUTHORIZATION admin;"

docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c \
"CREATE SCHEMA IF NOT EXISTS acervo AUTHORIZATION admin;"

docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c \
"CREATE SCHEMA IF NOT EXISTS emprestimos AUTHORIZATION admin;"

docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c \
"CREATE SCHEMA IF NOT EXISTS notificacoes AUTHORIZATION admin;"

echo "Schemas criados!"

# Verifica schemas
docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c '\dn'