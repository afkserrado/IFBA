# Busca os processos que estão usando as portas dos microsserviços
PIDS=$(lsof -t -i:8080 -i:8081 -i:8082 -i:8083 -i:8084 -i:8085)

# Mata os processos encontrados
if [ -n "$PIDS" ]; then
    kill $PIDS
    echo "Microsserviços encerrados."
else
    echo "Nenhum microsserviço encontrado nas portas configuradas."
fi