# Exibe o título.
echo "======================================================="
echo " INICIALIZAÇÃO DOS MICROSSERVIÇOS SPRING BOOT"
echo "======================================================="

# Procura a pasta backend a partir do diretório atual.

# Verifica se já está dentro da pasta backend.
if [ "$(basename "$PWD")" = "backend" ]; then

    BACKEND_DIR="$PWD"

# Verifica se está dentro de TRABALHO-PWEB e encontra backend.
elif [ "$(basename "$PWD")" = "TRABALHO-PWEB" ] && [ -d "backend" ]; then

    BACKEND_DIR="$PWD/backend"

# Verifica se encontra TRABALHO-PWEB/backend a partir do diretório atual.
elif [ -d "TRABALHO-PWEB/backend" ]; then

    BACKEND_DIR="$PWD/TRABALHO-PWEB/backend"

else

    echo "Erro: não foi possível localizar TRABALHO-PWEB/backend"
    exit 1

fi

# Entra no diretório do backend.
cd "$BACKEND_DIR" || exit

# Exibe o diretório utilizado.
echo "Executando na pasta:"
pwd
echo

# Busca processos utilizando as portas dos microsserviços.
PIDS=$(lsof -t -i:8080 -i:8081 -i:8082 -i:8083 -i:8084 -i:8085)

# Encerra os microsserviços em execução.
if [ -n "$PIDS" ]; then

    kill $PIDS
    echo "Microsserviços anteriores encerrados."

else

    echo "Nenhum microsserviço em execução encontrado."

fi

# Lista dos microsserviços que serão iniciados.
SERVICOS=(
    "usuarios-ms"
    "acervo-ms"
    "emprestimos-ms"
    "notificacoes-ms"
    "email-ms"
    "gateway-ms"
)

# Inicia cada microsserviço.
for servico in "${SERVICOS[@]}"; do

    # Define o caminho do microsserviço atual.
    CAMINHO="$BACKEND_DIR/$servico"

    # Verifica se o microsserviço existe.
    if [ -d "$CAMINHO" ]; then

        echo "Iniciando $servico..."

        # Entra na pasta do serviço e inicia o Spring Boot.
        # O log é salvo na pasta home do usuário.
        (
            cd "$CAMINHO" || exit
            bash mvnw spring-boot:run > "$HOME/${servico}.log" 2>&1
        ) &

        # Aguarda antes de iniciar o próximo serviço.
        sleep 3

    else

        echo "Aviso: $servico não encontrado."

    fi

done

# Exibe mensagem final.
echo
echo "Microsserviços iniciados."
echo "Logs disponíveis em:"
echo "$HOME/<nome-do-servico>.log"