# Guia de Execução — Extrator de Documentos de Auditoria

## 1. Execução Nativa (Ambiente Virtual Python)

### Ativar o ambiente virtual
* **Linux:** `source .venv/bin/activate`
* **Windows:** `.\.venv-win\Scripts\Activate.ps1`

### Executar o programa
* **Linux:** `python main.py`
* **Windows:** `python main.py`

---

## 2. Execução via Docker

### Linux
```bash
xhost +local:docker                     # 1. Liberar acesso ao servidor gráfico X11 (uma vez por sessão)
docker compose build                    # 2. Construir a imagem (a cada edição de código ou prompts)
docker compose run --rm auditoria-app   # 3. Executar o programa

### Windows
docker compose -f docker-compose.yml -f docker-compose.windows.yml build                  # 1. Construir a imagem (a cada edição de código ou prompts)
docker compose -f docker-compose.yml -f docker-compose.windows.yml run --rm auditoria-app  # 2. Executar o programa
```