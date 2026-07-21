#!/bin/bash

# Uso:
# 1. Altere a variável PROJETO com o caminho da pasta que contém TRABALHO-PWEB
# 2. Execute este script no terminal

PROJETO="/mnt/Arquivos_D0P6LA/Codes"

BACKEND="$PROJETO/TRABALHO-PWEB/backend"

if [ ! -d "$BACKEND" ]; then
    echo "Erro: backend não encontrado em:"
    echo "$BACKEND"
    exit 1
fi

echo "Backend encontrado:"
echo "$BACKEND"
echo

SERVICOS=(
    "usuarios-ms"
    "acervo-ms"
    "emprestimos-ms"
    "notificacoes-ms"
    "email-ms"
    "gateway-ms"
)

for servico in "${SERVICOS[@]}"; do

    CAMINHO="$BACKEND/$servico"

    if [ -d "$CAMINHO" ]; then
        echo "Iniciando $servico..."

        (
            cd "$CAMINHO" || exit
            bash mvnw spring-boot:run > "$HOME/${servico}.log" 2>&1
        ) &

        sleep 3

    else
        echo "Aviso: $servico não encontrado."
    fi

done

echo
echo "Processos dos microsserviços iniciados."
echo "Consulte os logs em:"
echo "$HOME/<nome-do-servico>.log"