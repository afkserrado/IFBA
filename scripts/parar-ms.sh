# Matar os processos

kill $(lsof -t -i:8080 -i:8081 -i:8082 -i:8083 -i:8084 -i:8085)

# Confirmar se foram interrompidos

lsof -i :8080
lsof -i :8081
lsof -i :8082
lsof -i :8083
lsof -i :8084
lsof -i :8085