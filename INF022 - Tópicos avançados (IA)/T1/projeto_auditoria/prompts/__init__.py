"""Módulo responsável pelos tipos de documentos e carregamento de prompts."""

from enum import Enum
from pathlib import Path

# Diretório absoluto da pasta 'prompts' onde este arquivo (__init__.py) está localizado
DIRETORIO_PROMPTS: Path = Path(__file__).resolve().parent

class TipoDocumento(Enum):
    """Tipos de documentos institucionais de auditoria suportados pelo sistema.

    O nome do membro (em MAIÚSCULAS, sem preposições) define automaticamente o nome
    do arquivo .txt esperado na pasta 'prompts/' (em minúsculas).
    O valor (str) é o nome amigável exibido ao usuário na interface gráfica.
    """

    ENTENDIMENTO_OBJETO = "Entendimento do Objeto"
    MAPEAMENTO_PROCESSO = "Mapeamento do Processo"
    MATRIZ_RISCOS_CONTROLES = "Matriz de Riscos e Controles"
    MATRIZ_PLANEJAMENTO = "Matriz de Planejamento"
    QUADRO_RESULTADOS = "Quadro de Resultados"
    RELATORIO_AUDITORIA = "Relatório de Auditoria"

# Dicionário que associa cada TipoDocumento ao nome padronizado do seu arquivo .txt
# Exemplo: TipoDocumento.ENTENDIMENTO_OBJETO -> "entendimento_objeto.txt"
PROMPTS_POR_DOCUMENTO: dict[TipoDocumento, str] = {
    tipo: f"{tipo.name.lower()}.txt" for tipo in TipoDocumento
}

def listar_tipos_disponiveis() -> list[TipoDocumento]:
    """Retorna apenas os tipos de documentos cujo arquivo .txt existe na pasta prompts/."""
    return [
        tipo
        for tipo in TipoDocumento
        if (DIRETORIO_PROMPTS / PROMPTS_POR_DOCUMENTO[tipo]).is_file()
    ]

def carregar_prompt(tipo_documento: TipoDocumento) -> str:
    """Lê e retorna o conteúdo do arquivo de prompt associado ao TipoDocumento.

    Args:
        tipo_documento: Membro do Enum TipoDocumento (ex: TipoDocumento.ENTENDIMENTO_OBJETO).

    Returns:
        Texto do prompt decodificado em UTF-8 e sem espaços sobrando nas extremidades.

    Raises:
        TypeError: Se o argumento informado não for um membro de TipoDocumento.
        FileNotFoundError: Se o arquivo .txt correspondente ainda não tiver sido criado.
    """
    if not isinstance(tipo_documento, TipoDocumento):
        raise TypeError(
            f"Era esperado um TipoDocumento, mas foi recebido: {type(tipo_documento).__name__}"
        )

    nome_arquivo = PROMPTS_POR_DOCUMENTO[tipo_documento]
    caminho_prompt = DIRETORIO_PROMPTS / nome_arquivo

    if not caminho_prompt.is_file():
        raise FileNotFoundError(
            f"Prompt não encontrado para '{tipo_documento.value}'. "
            f"Crie o arquivo: {caminho_prompt}"
        )

    return caminho_prompt.read_text(encoding="utf-8").strip()

def montar_prompt_extracao(
    conteudo_documento: str,
    tipo_documento: TipoDocumento,
) -> str:
    """Combina o texto extraído do documento com as instruções do TipoDocumento.

    Args:
        conteudo_documento: Texto completo extraído do arquivo .docx ou .pdf.
        tipo_documento: Tipo de documento institucional selecionado na interface.

    Returns:
        Prompt final estruturado pronto para envio ao modelo de IA.
    """
    instrucoes = carregar_prompt(tipo_documento)

    return (
        "Analise os documentos de referência fornecidos abaixo e responda à solicitação do usuário "
        "baseando-se estritamente no conteúdo fornecido.\n\n"
        "=== DOCUMENTOS DE REFERÊNCIA ===\n"
        f"{conteudo_documento}\n"
        "=== FIM DOS DOCUMENTOS DE REFERÊNCIA ===\n\n"
        f"Solicitação do usuário: {instrucoes}"
    )