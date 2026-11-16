"""Módulo responsável pela extração de texto (nativo e via OCR) de documentos .docx e .pdf."""

from importlib import import_module
from pathlib import Path
import zipfile

# Limiares mínimos de texto nativo para decidir quando acionar o OCR local
MINIMO_CARACTERES_TEXTO_NATIVO: int = 100
MINIMO_CARACTERES_PAGINA_PDF: int = 50

# ==============================================================================
# Verificação de bibliotecas opcionais
# Permite que o sistema informe exatamente qual pacote instalar caso esteja ausente.
# ==============================================================================
try:
    Document = import_module("docx").Document
    TEM_PYTHON_DOCX = True
except ImportError:
    TEM_PYTHON_DOCX = False

try:
    pymupdf = import_module("pymupdf")
    TEM_PYMUPDF = True
except ImportError:
    TEM_PYMUPDF = False

try:
    RapidOCR = import_module("rapidocr_onnxruntime").RapidOCR
    TEM_RAPIDOCR = True
except ImportError:
    TEM_RAPIDOCR = False


def executar_ocr_em_imagens(lista_bytes_imagens: list[bytes]) -> str:
    """Executa OCR local em uma lista de imagens (em bytes) e retorna o texto extraído.

    Args:
        lista_bytes_imagens: Lista contendo os bytes brutos de cada imagem.

    Returns:
        Texto reconhecido concatenado por imagem/página.

    Raises:
        RuntimeError: Se houver imagens para processar, mas o RapidOCR não estiver instalado.
    """
    if not lista_bytes_imagens:
        return ""

    if not TEM_RAPIDOCR:
        raise RuntimeError(
            "O documento contém imagens/páginas escaneadas, mas o motor de OCR não está instalado.\n"
            "Execute no terminal: pip install rapidocr-onnxruntime"
        )

    motor_ocr = RapidOCR()
    textos_ocr: list[str] = []

    for idx, img_bytes in enumerate(lista_bytes_imagens, start=1):
        resultado, _ = motor_ocr(img_bytes)
        if resultado:
            # Cada item de 'resultado' tem o formato: [coordenadas_caixa, texto_reconhecido, confianca]
            linhas = [item[1] for item in resultado if item[1].strip()]
            if linhas:
                textos_ocr.append(
                    f"[Texto extraído por OCR - Imagem/Página {idx}]\n" + "\n".join(linhas)
                )

    return "\n\n".join(textos_ocr).strip()


def ler_docx(caminho: Path) -> str:
    """Extrai texto de parágrafos e tabelas de um .docx e aplica OCR em imagens embutidas.

    Args:
        caminho: Caminho para o arquivo .docx.

    Returns:
        Texto completo consolidado (texto nativo + texto extraído de imagens via OCR).

    Raises:
        RuntimeError: Se a biblioteca python-docx não estiver instalada.
    """
    if not TEM_PYTHON_DOCX:
        raise RuntimeError(
            "Para ler arquivos .docx, instale o python-docx executando no terminal:\n"
            "pip install python-docx"
        )

    partes_texto: list[str] = []
    doc = Document(str(caminho))

    for paragrafo in doc.paragraphs:
        if paragrafo.text.strip():
            partes_texto.append(paragrafo.text)

    for tabela in doc.tables:
        for linha in tabela.rows:
            celulas = [celula.text.strip() for celula in linha.cells if celula.text.strip()]
            if celulas:
                partes_texto.append(" | ".join(celulas))

    texto_nativo = "\n".join(partes_texto).strip()

    # Verifica se há imagens embutidas dentro da pasta interna word/media/
    imagens_bytes: list[bytes] = []
    extensoes_validas = (".png", ".jpg", ".jpeg", ".bmp", ".tiff", ".webp")
    with zipfile.ZipFile(caminho) as arquivo_zip:
        for nome_interno in sorted(arquivo_zip.namelist()):
            if nome_interno.startswith("word/media/") and nome_interno.lower().endswith(extensoes_validas):
                imagens_bytes.append(arquivo_zip.read(nome_interno))

    texto_ocr = ""
    if imagens_bytes and (len(texto_nativo) < MINIMO_CARACTERES_TEXTO_NATIVO or TEM_RAPIDOCR):
        print(f"Executando OCR local em {len(imagens_bytes)} imagem(ns) encontrada(s) no .docx...")
        texto_ocr = executar_ocr_em_imagens(imagens_bytes)

    return "\n\n".join(filter(None, [texto_nativo, texto_ocr])).strip()


def ler_pdf(caminho: Path) -> str:
    """Extrai texto selecionável de cada página do PDF e aplica OCR nas páginas escaneadas.

    Args:
        caminho: Caminho para o arquivo .pdf.

    Returns:
        Texto completo do PDF com marcação de páginas.

    Raises:
        RuntimeError: Se o PyMuPDF não estiver instalado.
    """
    if not TEM_PYMUPDF:
        raise RuntimeError(
            "Para ler arquivos .pdf, instale o PyMuPDF executando no terminal:\n"
            "pip install pymupdf"
        )

    partes_finais: list[str] = []
    # 200 DPI oferece ótimo equilíbrio entre velocidade e precisão no OCR local
    matriz_zoom = pymupdf.Matrix(200 / 72, 200 / 72)

    with pymupdf.open(caminho) as doc_pdf:
        for num_pagina, pagina in enumerate(doc_pdf, start=1):
            texto_pagina = pagina.get_text("text").strip()

            # Se a página tem texto selecionável suficiente, usa diretamente
            if len(texto_pagina) >= MINIMO_CARACTERES_PAGINA_PDF:
                partes_finais.append(f"--- Página {num_pagina} ---\n{texto_pagina}")
            else:
                # Página sem camada de texto (escaneada/imagem): renderiza em PNG e roda OCR
                print(f"Página {num_pagina} sem texto selecionável detectada. Executando OCR local...")
                pix = pagina.get_pixmap(matrix=matriz_zoom)
                texto_ocr_pagina = executar_ocr_em_imagens([pix.tobytes("png")])
                if texto_ocr_pagina:
                    partes_finais.append(f"--- Página {num_pagina} (OCR) ---\n{texto_ocr_pagina}")

    return "\n\n".join(partes_finais).strip()


def extrair_texto_documento(caminho_arquivo: str | Path) -> str:
    """Função principal do módulo: identifica a extensão (.docx ou .pdf) e extrai o texto completo.

    Args:
        caminho_arquivo: Caminho (string ou Path) do documento selecionado pelo usuário.

    Returns:
        Conteúdo textual completo pronto para ser enviado ao prompt.

    Raises:
        FileNotFoundError: Se o arquivo não existir.
        ValueError: Se a extensão não for suportada ou se nenhum texto puder ser extraído.
    """
    caminho = Path(caminho_arquivo)

    if not caminho.is_file():
        raise FileNotFoundError(f"O arquivo '{caminho}' não foi encontrado.")

    extensao = caminho.suffix.lower()

    if extensao == ".docx":
        conteudo = ler_docx(caminho)
    elif extensao == ".pdf":
        conteudo = ler_pdf(caminho)
    else:
        raise ValueError(
            f"Formato '{extensao}' não suportado. Utilize apenas arquivos .docx ou .pdf."
        )

    if not conteudo:
        raise ValueError(
            "Não foi possível extrair nenhum texto do documento (nem nativo, nem via OCR)."
        )

    return conteudo