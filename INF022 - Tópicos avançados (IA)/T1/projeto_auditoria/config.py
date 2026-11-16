"""Configurações centrais do sistema de análise de documentos."""

from pathlib import Path

# Servidores Ollama em ordem de prioridade (tenta o local primeiro; se falhar, tenta o remoto)
SERVIDORES_OLLAMA: list[str] = [
    "http://localhost:11434",
    "https://ollama-api.ifba.edu.br",
]

# Modelos de LLM explicitamente habilitados para seleção na interface
MODELOS_DISPONIVEIS: list[str] = [
    "qwen3.5:4b",
    "gemma4:e4b",
    "deepseek-r1:8b",
    "gemma4:12b",
    "gemma4:26b",
]

# Parâmetros padrão de geração
# Temperatura baixa (0.0 a 1.0): reduz aleatoriedade e prioriza respostas fiéis ao documento
TEMPERATURA_PADRAO: float = 0.1

# Janela de contexto (num_ctx): quantidade máxima de tokens (entrada + saída) processados de uma vez
CONTEXTO_PADRAO: int = 8192

# Tempo limite (em segundos) aguardando resposta do servidor antes de acusar erro de conexão (300s = 5 min)
TIMEOUT_REQUISICAO: int = 300

def obter_pasta_downloads() -> Path:
    """Retorna o caminho da pasta Downloads do usuário (compatível com Linux e Windows)."""
    pasta_downloads = Path.home() / "Downloads"
    pasta_downloads.mkdir(parents=True, exist_ok=True)
    return pasta_downloads