# Exibe o título da verificação.
echo "================================================="
echo " VERIFICAÇÃO DOS MICROSSERVIÇOS"
echo "================================================="

# Função para verificar se uma porta está em uso.
verificar_servico() {

    NOME=$1
    PORTA=$2

    echo

    # Verifica se existe algum processo usando a porta.
    if lsof -i :$PORTA >/dev/null 2>&1; then
        echo "$NOME ($PORTA): EXECUTANDO"
    else
        echo "$NOME ($PORTA): PARADO"
    fi
}

# Verifica cada microsserviço.
verificar_servico "Gateway MS" 8080
verificar_servico "Usuários MS" 8081
verificar_servico "Acervo MS" 8082
verificar_servico "Empréstimos MS" 8083
verificar_servico "Notificações MS" 8084
verificar_servico "Email MS" 8085

echo
echo "Verificação concluída."