# Exibe o título.
echo "======================================================="
echo " INICIALIZAÇÃO DOS CONTÊINERES"
echo " PostgreSQL e RabbitMQ"
echo "======================================================="

# Verifica se já está dentro de infra e possui o docker-compose.yml.
if [ "$(basename "$PWD")" = "infra" ] && [ -f "docker-compose.yml" ]; then

    INFRA_DIR="$PWD"

# Verifica se está dentro de TRABALHO-PWEB e encontra a pasta infra.
elif [ "$(basename "$PWD")" = "TRABALHO-PWEB" ] && [ -d "infra" ]; then

    INFRA_DIR="$PWD/infra"

# Verifica se encontra TRABALHO-PWEB/infra a partir do diretório atual.
elif [ -d "TRABALHO-PWEB/infra" ]; then

    INFRA_DIR="$PWD/TRABALHO-PWEB/infra"

else

    echo "Erro: não foi possível localizar TRABALHO-PWEB/infra"
    exit 1
fi

# Entra no diretório da infraestrutura.
cd "$INFRA_DIR" || exit

# Exibe o diretório utilizado.
echo "Executando na pasta:"
pwd

# Remove containers, rede e volumes existentes.
docker compose down -v

# Inicia os serviços em segundo plano.
docker compose up -d

# Aguarda o PostgreSQL ficar disponível.
echo "Aguardando PostgreSQL iniciar..."

until docker exec biblioteca_postgres pg_isready -U admin >/dev/null 2>&1
do
    sleep 2
done

echo "PostgreSQL iniciado!"

# Aguarda o container responsável pela criação dos schemas finalizar.
echo "Aguardando criação dos schemas..."

docker wait biblioteca_db_init >/dev/null

echo "Schemas criados!"

# Lista os schemas existentes no banco.
docker exec biblioteca_postgres psql -U admin -d biblioteca -c '\dn'

echo "Infraestrutura iniciada com sucesso!"