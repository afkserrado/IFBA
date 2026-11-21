Para entrar no ambiente virtual Python: 

Linux -> source .venv/bin/activate
Windows -> .\.venv-win\Scripts\Activate.ps1

Para executar o programa:

Linux -> 
Windows -> 

Construção da imagem:
docker compose -f docker-compose.yml -f docker-compose.windows.yml build

Execução do programa:
docker compose -f docker-compose.yml -f docker-compose.windows.yml run --rm auditoria-app