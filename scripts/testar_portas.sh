echo "================================================="
echo " VERIFICAÇÃO DOS MICROSSERVIÇOS"
echo "================================================="

echo
echo "Gateway MS (8080)"
sudo lsof -i :8080
curl -i -s http://localhost:8080/actuator/health || echo "Gateway sem resposta"

echo
echo "Usuários MS (8081)"
sudo lsof -i :8081
curl -i -s http://localhost:8081/actuator/health || echo "Usuários sem resposta"

echo
echo "Acervo MS (8082)"
sudo lsof -i :8082
curl -i -s http://localhost:8082/actuator/health || echo "Acervo sem resposta"

echo
echo "Empréstimos MS (8083)"
sudo lsof -i :8083
curl -i -s http://localhost:8083/actuator/health || echo "Empréstimos sem resposta"

echo
echo "Notificações MS (8084)"
sudo lsof -i :8084
curl -i -s http://localhost:8084/actuator/health || echo "Notificações sem resposta"

echo
echo "Email MS (8085)"
sudo lsof -i :8085
curl -i -s http://localhost:8085/actuator/health || echo "Email sem resposta"