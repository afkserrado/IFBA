"""Configurações centrais do sistema de análise de documentos."""

import os
from pathlib import Path
import requests

# Permite apontar para o host do Docker (ex: http://host.docker.internal:11434) sem quebrar o uso nativo
_SERVIDOR_AMBIENTE = os.getenv("OLLAMA_HOST_URL", "").strip()

# Fontes de modelos Ollama disponíveis para seleção na interface:
# - "Local": prioriza OLLAMA_HOST_URL (quando em container Docker) e depois localhost
# - "IFBA (Remoto)": servidor institucional do IFBA
FONTES_OLLAMA: dict[str, list[str]] = {
    "Local": [
        *([_SERVIDOR_AMBIENTE] if _SERVIDOR_AMBIENTE else []),
        "http://localhost:11434",
    ],
    "IFBA (Remoto)": [
        "https://ollama-api.ifba.edu.br",
    ],
}

# Lista unificada mantida por compatibilidade (Local -> IFBA)
SERVIDORES_OLLAMA: list[str] = [
    url for urls in FONTES_OLLAMA.values() for url in urls
]

# Parâmetros padrão de geração
TEMPERATURA_PADRAO: float = 0.1
TOP_K_PADRAO: int = 40
TOP_P_PADRAO: float = 0.90

# Janela de contexto (num_ctx): quantidade máxima de tokens (entrada + saída) processados de uma vez
CONTEXTO_PADRAO: int = 65536

# Tempo limite (em segundos) aguardando resposta da inferência (300s = 5 min)
TIMEOUT_REQUISICAO: int = 300

def obter_modelos_disponiveis(fonte: str | None = None, timeout: int = 3) -> list[str]:
    """Consulta os servidores da fonte informada (ou todos, se None) e retorna os modelos instalados no primeiro ativo."""
    servidores = FONTES_OLLAMA.get(fonte, SERVIDORES_OLLAMA) if fonte else SERVIDORES_OLLAMA
    for servidor in servidores:
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