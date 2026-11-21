"""Configurações centrais do sistema de análise de documentos."""

import os
from pathlib import Path
import requests

# Permite apontar para o host do Docker (ex: http://host.docker.internal:11434) sem quebrar o uso nativo
_SERVIDOR_AMBIENTE = os.getenv("OLLAMA_HOST_URL", "").strip()

# Servidores Ollama em ordem de prioridade:
# 1. Host do Docker (quando OLLAMA_HOST_URL estiver definida)
# 2. Máquina local (execução nativa fora do Docker)
# 3. Servidor remoto institucional (IFBA)
SERVIDORES_OLLAMA: list[str] = [
    *([_SERVIDOR_AMBIENTE] if _SERVIDOR_AMBIENTE else []),
    "http://localhost:11434",
    "https://ollama-api.ifba.edu.br",
]

# Parâmetros padrão de geração
TEMPERATURA_PADRAO: float = 0.1
TOP_K_PADRAO: int = 40
TOP_P_PADRAO: float = 0.90

# Janela de contexto (num_ctx): quantidade máxima de tokens (entrada + saída) processados de uma vez
CONTEXTO_PADRAO: int = 65536

# Tempo limite (em segundos) aguardando resposta da inferência (300s = 5 min)
TIMEOUT_REQUISICAO: int = 300

def obter_modelos_disponiveis(timeout: int = 3) -> list[str]:
    """Consulta os servidores configurados em ordem de prioridade e retorna os modelos instalados no primeiro ativo."""
    for servidor in SERVIDORES_OLLAMA:
        try:
            resposta = requests.get(f"{servidor}/api/tags", timeout=timeout)
            resposta.raise_for_status()
            modelos = [modelo["name"] for modelo in resposta.json().get("models", [])]
            if modelos:
                return modelos
        except requests.RequestException:
            continue
    return []

def obter_pasta_downloads() -> Path:
    """Retorna o caminho da pasta Downloads do usuário (compatível com Linux e Windows)."""
    pasta_downloads = Path.home() / "Downloads"
    pasta_downloads.mkdir(parents=True, exist_ok=True)
    return pasta_downloads

MODELOS_DISPONIVEIS: list[str] = obter_modelos_disponiveis()