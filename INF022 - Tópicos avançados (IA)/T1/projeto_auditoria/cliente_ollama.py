"""Módulo responsável pela comunicação com o Ollama e salvamento do JSON gerado."""

from datetime import datetime
import json
from pathlib import Path
import re
import urllib.error
import urllib.request

from config import (
    CONTEXTO_PADRAO,
    SERVIDORES_OLLAMA,
    TEMPERATURA_PADRAO,
    TIMEOUT_REQUISICAO,
    obter_pasta_downloads,
)

def detectar_servidor_ativo(servidores: list[str] | None = None) -> str:
    """Testa os servidores Ollama em ordem de prioridade e retorna a URL do primeiro ativo.

    Args:
        servidores: Lista opcional de URLs. Se omitida, usa SERVIDORES_OLLAMA de config.py.

    Returns:
        URL base do servidor acessível (ex: 'http://localhost:11434').

    Raises:
        ConnectionError: Se nenhum servidor responder dentro do tempo limite.
    """
    lista_servidores = servidores or SERVIDORES_OLLAMA

    for servidor in lista_servidores:
        try:
            with urllib.request.urlopen(f"{servidor}/api/tags", timeout=3) as resposta:
                if resposta.status == 200:
                    return servidor
        except Exception:
            continue

    raise ConnectionError(
        "Não foi possível conectar a nenhum servidor Ollama (nem local, nem IFBA).\n"
        "Verifique sua conexão de rede ou se o serviço local do Ollama está em execução."
    )

def _limpar_resposta_json(texto_bruto: str) -> str:
    """Remove eventuais blocos <think>...</think> ou cercas Markdown da resposta do modelo."""
    sem_think = re.sub(r"<think>.*?</think>", "", texto_bruto, flags=re.DOTALL).strip()
    sem_markdown = re.sub(r"^```(?:json)?\s*|\s*```$", "", sem_think).strip()
    return sem_markdown

def consultar_ollama(
    prompt_completo: str,
    modelo: str,
    url_servidor: str | None = None,
    temperatura: float = TEMPERATURA_PADRAO,
    num_ctx: int = CONTEXTO_PADRAO,
) -> dict:
    """Envia o prompt ao servidor Ollama escolhido e retorna o resultado convertido em dicionário.

    Args:
        prompt_completo: Texto final contendo o documento e as instruções do prompt.
        modelo: Nome/tag do modelo escolhido pelo usuário (ex: 'qwen3.5:4b').
        url_servidor: URL do servidor Ollama. Se None, detecta automaticamente.
        temperatura: Grau de aleatoriedade da resposta.
        num_ctx: Tamanho da janela de contexto em tokens.

    Returns:
        Dicionário Python validado a partir do JSON retornado pela IA.

    Raises:
        RuntimeError: Se houver falha na comunicação ou se a resposta não for um JSON válido.
    """
    servidor_ativo = url_servidor or detectar_servidor_ativo()
    url_chat = f"{servidor_ativo}/api/chat"

    payload = {
        "model": modelo,
        "messages": [{"role": "user", "content": prompt_completo}],
        "stream": True,
        "format": "json",
        # Desativa o raciocínio separado nos modelos compatíveis (Gemma 4, Qwen 3.5),
        # evitando que consumam a janela de contexto antes de escrever o JSON final.
        "think": False,
        "options": {
            "temperature": temperatura,
            "num_ctx": num_ctx,
        },
    }

    requisicao = urllib.request.Request(
        url_chat,
        data=json.dumps(payload).encode("utf-8"),
        headers={"Content-Type": "application/json"},
    )

    print(f"\nEnviando documento para '{modelo}' em {servidor_ativo}...")
    partes_resposta: list[str] = []
    em_raciocinio = False
    motivo_termino = ""

    try:
        with urllib.request.urlopen(requisicao, timeout=TIMEOUT_REQUISICAO) as resposta:
            for linha in resposta:
                if not linha:
                    continue

                pedaco = json.loads(linha.decode("utf-8"))
                mensagem = pedaco.get("message", {})

                # Modelos como deepseek-r1 ignoram 'think: False' e emitem tokens em 'thinking'
                if mensagem.get("thinking"):
                    if not em_raciocinio:
                        print("[Modelo raciocinando...]", end="", flush=True)
                        em_raciocinio = True

                conteudo = mensagem.get("content", "")
                if conteudo:
                    if em_raciocinio:
                        print("\n", end="", flush=True)
                        em_raciocinio = False
                    print(conteudo, end="", flush=True)
                    partes_resposta.append(conteudo)

                if pedaco.get("done"):
                    motivo_termino = pedaco.get("done_reason", "")
                    break
        print("\n")

    except urllib.error.HTTPError as erro_http:
        detalhe = erro_http.read().decode("utf-8", errors="ignore")
        raise RuntimeError(
            f"O servidor Ollama retornou erro HTTP {erro_http.code}: {detalhe}"
        ) from erro_http
    except Exception as erro:
        raise RuntimeError(f"Erro durante a comunicação com o Ollama: {erro}") from erro

    texto_final = _limpar_resposta_json("".join(partes_resposta))

    try:
        return json.loads(texto_final)
    except json.JSONDecodeError as erro_json:
        aviso_limite = (
            f" (A janela de contexto de {num_ctx} tokens foi atingida antes de concluir o JSON.)"
            if motivo_termino == "length"
            else ""
        )
        raise RuntimeError(
            f"A resposta do modelo '{modelo}' não pôde ser convertida em JSON válido.{aviso_limite}\n"
            f"Conteúdo recebido:\n{texto_final or '[vazio]'}"
        ) from erro_json

def salvar_resultado_json(dados: dict, caminho_documento_origem: str | Path) -> Path:
    """Salva o dicionário extraído como arquivo .json na pasta Downloads do sistema.

    Args:
        dados: Dicionário com os campos extraídos pela IA.
        caminho_documento_origem: Caminho do arquivo original analisado (usado para nomear o JSON).

    Returns:
        Caminho completo (Path) do arquivo .json salvo em Downloads.
    """
    pasta_downloads = obter_pasta_downloads()
    nome_base = Path(caminho_documento_origem).stem
    carimbo_tempo = datetime.now().strftime("%Y%m%d_%H%M%S")

    caminho_saida = pasta_downloads / f"{nome_base}_extracao_{carimbo_tempo}.json"

    conteudo_json = json.dumps(dados, indent=2, ensure_ascii=False)
    caminho_saida.write_text(conteudo_json, encoding="utf-8")

    return caminho_saida