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

    mvn clean install compile

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
    "usuarios-ms"
    "acervo-ms"
    "emprestimos-ms"
    "notificacoes-ms"
    "email-ms"
    "gateway-ms"
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