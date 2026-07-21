#!/bin/bash

BASE_URL="http://localhost:8080"

echo "================================================="
echo " LIMPEZA DO BANCO"
echo "================================================="

docker exec -i biblioteca_postgres psql -U admin -d biblioteca <<EOF
TRUNCATE TABLE emprestimos.emprestimos RESTART IDENTITY CASCADE;
TRUNCATE TABLE acervo.livros RESTART IDENTITY CASCADE;

DELETE FROM usuarios.usuarios
WHERE email <> 'admin@biblioteca.com';

ALTER SEQUENCE usuarios.usuarios_id_seq RESTART WITH 2;
EOF


request() {

  echo -e "\n\n$1"
  echo "$2 $3"
  echo "----------------------------------------"

  AUTH_HEADER=""

  if [ -n "$TOKEN" ]; then
    AUTH_HEADER="-H \"Authorization: Bearer $TOKEN\""
  fi


  if [ -z "$4" ]; then

    eval curl -s -X "$2" "\"$BASE_URL$3\"" \
      $AUTH_HEADER | jq

  else

    eval curl -s -X "$2" "\"$BASE_URL$3\"" \
      $AUTH_HEADER \
      -H "\"Content-Type: application/json\"" \
      -d "'$4'" | jq

  fi
}



echo
echo "================================================="
echo " 1. LOGIN ADMIN INICIAL"
echo "================================================="


LOGIN=$(curl -s -X POST "$BASE_URL/auth/login" \
-H "Content-Type: application/json" \
-d '
{
 "email":"admin@biblioteca.com",
 "senha":"SENHA_DO_ENV"
}')


echo "$LOGIN" | jq


TOKEN=$(echo "$LOGIN" | jq -r '.token')


echo
echo "TOKEN:"
echo "$TOKEN"



echo
echo "================================================="
echo " 2. CADASTRO DE USUÁRIOS"
echo "================================================="


request "1. Criar Vinicius USER" POST "/api/v1/usuarios" '
{
  "cpf":"22222222222",
  "nome":"Vinicius",
  "email":"vinicius@email.com",
  "senha":"123456",
  "role":"USER"
}'


request "2. Criar Anna USER" POST "/api/v1/usuarios" '
{
  "cpf":"33333333333",
  "nome":"Anna",
  "email":"anna@email.com",
  "senha":"123456",
  "role":"USER"
}'



echo
echo "================================================="
echo " 3. TESTES USUÁRIOS"
echo "================================================="


request "3. Buscar admin inicial por ID" GET "/api/v1/usuarios/1"

request "4. Buscar Vinicius por ID" GET "/api/v1/usuarios/2"

request "5. Listar usuários" GET "/api/v1/usuarios"

request "6. Buscar por email Vinicius" GET "/api/v1/usuarios/busca-email?email=vinicius@email.com"

request "7. Validar situação cadastral Vinicius" GET "/api/v1/usuarios/2/validar-situacao"


request "8. Atualizar usuário Vinicius" PUT "/api/v1/usuarios/2" '
{
 "nome":"Vinicius Atualizado",
 "email":"vinicius@email.com"
}'



echo
echo "================================================="
echo " 4. CADASTRO DE LIVROS"
echo "================================================="


request "9. Criar livro 1" POST "/api/v1/livros" '
{
"title":"Clean Code",
"autor":"Robert C. Martin",
"isbn":"9780132350884",
"quantidadeDisponivel":5
}'


request "10. Criar livro 2" POST "/api/v1/livros" '
{
"title":"Java Efetivo",
"autor":"Joshua Bloch",
"isbn":"9788550804628",
"quantidadeDisponivel":3
}'


request "11. Criar livro 3" POST "/api/v1/livros" '
{
"title":"Spring Boot em Ação",
"autor":"Craig Walls",
"isbn":"9781617292545",
"quantidadeDisponivel":4
}'


request "12. Criar livro 4" POST "/api/v1/livros" '
{
"title":"Código Limpo",
"autor":"Robert C. Martin",
"isbn":"9788576082675",
"quantidadeDisponivel":2
}'


request "13. Criar livro 5" POST "/api/v1/livros" '
{
"title":"Design Patterns",
"autor":"Gang of Four",
"isbn":"9780201633610",
"quantidadeDisponivel":3
}'



echo
echo "================================================="
echo " 5. CONSULTAS ACERVO"
echo "================================================="


request "14. Listar livros" GET "/api/v1/livros"

request "15. Buscar por ISBN" GET "/api/v1/livros/isbn/9780132350884"

request "16. Buscar por título" GET "/api/v1/livros/titulo?titulo=Clean"

request "17. Buscar por autor" GET "/api/v1/livros/autor?autor=Robert"

request "18. Ordenar livros" GET "/api/v1/livros?ordenacao=titulo"

request "19. Ver disponibilidade livro 1" GET "/api/v1/livros/1/disponibilidade"



echo
echo "================================================="
echo " 6. EMPRÉSTIMOS"
echo "================================================="


request "20. Criar empréstimo Vinicius livro 1" POST "/api/v1/emprestimos" '
{
 "usuarioId":2,
 "livroId":1,
 "dataEmprestimo":"2026-07-21",
 "dataPrevistaDevolucao":"2026-08-01"
}'


request "21. Listar empréstimos" GET "/api/v1/emprestimos"

request "22. Buscar empréstimos usuário Vinicius" GET "/api/v1/emprestimos/usuario/2"

request "23. Verificar livro emprestado" GET "/api/v1/emprestimos/livros/1/ativos/existe"

request "24. Devolver livro" POST "/api/v1/emprestimos/1/devolucao"

request "25. Verificar novamente disponibilidade" GET "/api/v1/livros/1/disponibilidade"



echo
echo "================================================="
echo " 7. CANCELAMENTO"
echo "================================================="


request "26. Novo empréstimo Anna livro 2" POST "/api/v1/emprestimos" '
{
 "usuarioId":3,
 "livroId":2,
 "dataEmprestimo":"2026-07-21",
 "dataPrevistaDevolucao":"2026-08-01"
}'


request "27. Cancelar empréstimo" POST "/api/v1/emprestimos/2/cancelamento"



echo
echo "================================================="
echo " 8. TESTES DE ERRO"
echo "================================================="


request "28. Livro inválido" POST "/api/v1/livros" '
{
"title":"",
"autor":"",
"isbn":"",
"quantidadeDisponivel":-1
}'


request "29. Livro inexistente" GET "/api/v1/livros/isbn/000000"

request "30. Usuário inexistente" GET "/api/v1/usuarios/999"


request "31. Empréstimo com livro inexistente" POST "/api/v1/emprestimos" '
{
 "usuarioId":2,
 "livroId":999,
 "dataEmprestimo":"2026-07-21",
 "dataPrevistaDevolucao":"2026-08-01"
}'


request "32. Excluir usuário inexistente" DELETE "/api/v1/usuarios/999"



echo
echo "================================================="
echo " TESTES FINALIZADOS"
echo "================================================="