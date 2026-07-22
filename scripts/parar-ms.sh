# Matar os processos

kill $(lsof -t -i:8080 -i:8081 -i:8082 -i:8083 -i:8084 -i:8085)

# Confirmar se foram interrompidos

lsof -i :8080 # gateway-ms
lsof -i :8081 # usuarios-ms
lsof -i :8082 # acervo-ms
lsof -i :8083 # emprestimos-ms
lsof -i :8084 # notificacoes-ms
lsof -i :8085 # email-ms