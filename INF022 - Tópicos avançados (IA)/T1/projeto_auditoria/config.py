"""Configurações centrais do sistema de análise de documentos."""

from pathlib import Path
import requests

# Servidores Ollama em ordem de prioridade (tenta o local primeiro; se falhar, tenta o remoto)
SERVIDORES_OLLAMA: list[str] = [
    "http://localhost:11434",
    "https://ollama-api.ifba.edu.br",
]

# Parâmetros padrão de geração
# Temperatura baixa (0.0 a 1.0): reduz aleatoriedade e prioriza respostas fiéis ao documento
TEMPERATURA_PADRAO: float = 0.1
TOP_K_PADRAO: int = 40
TOP_P_PADRAO: float = 0.90

# Janela de contexto (num_ctx): quantidade máxima de tokens (entrada + saída) processados de uma vez
CONTEXTO_PADRAO: int = 65536

# Tempo limite (em segundos) aguardando resposta do servidor antes de acusar erro de conexão (300s = 5 min)
TIMEOUT_REQUISICAO: int = 300

def obter_modelos_locais(url_servidor: str = "http://localhost:11434", timeout: int = 5) -> list[str]:
    """Consulta o servidor Ollama local e retorna a lista de modelos instalados."""
    try:
        # O endpoint /api/tags lista todos os modelos locais
        resposta = requests.get(f"{url_servidor}/api/tags", timeout=timeout)
        resposta.raise_for_status()
        
        dados = resposta.json()
        # Extrai apenas o nome ('name') de cada modelo retornado
        return [modelo["name"] for modelo in dados.get("models", [])]
        
    except requests.RequestException:
        # Retorna uma lista vazia ou logs se o servidor estiver offline
        return []

def obter_pasta_downloads() -> Path:
    """Retorna o caminho da pasta Downloads do usuário (compatível com Linux e Windows)."""
    pasta_downloads = Path.home() / "Downloads"
    pasta_downloads.mkdir(parents=True, exist_ok=True)
    return pasta_downloads

# Chamada movida para o final do arquivo, após o TIMEOUT_REQUISICAO ser definido
MODELOS_DISPONIVEIS: list[str] = obter_modelos_locais(SERVIDORES_OLLAMA[0], TIMEOUT_REQUISICAO)