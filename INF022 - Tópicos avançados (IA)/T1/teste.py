import io
import json
import os
import urllib.request
import zipfile
import xml.etree.ElementTree as ET

# ==========================================================
# Verificação de bibliotecas opcionais
try:
    from docx import Document
    TEM_PYTHON_DOCX = True
except ImportError:
    TEM_PYTHON_DOCX = False

try:
    import fitz  # PyMuPDF (leitura de PDF e renderização de páginas em imagem)
    TEM_PYMUPDF = True
except ImportError:
    TEM_PYMUPDF = False

try:
    from rapidocr_onnxruntime import RapidOCR
    TEM_RAPIDOCR = True
except ImportError:
    TEM_RAPIDOCR = False

# ==========================================================
# 1. Configurações de Arquivo e Servidores
CAMINHO_ARQUIVO = r"C:\Users\aluno.ssa\Downloads\1.2. Entendimento do objeto.docx"

# Lista de servidores em ordem de prioridade (tenta o local primeiro; se falhar, tenta o remoto)
SERVIDORES_OLLAMA = [
    "http://localhost:11434",
    "https://ollama-api.ifba.edu.br"
]
# ==========================================================


def executar_ocr_em_imagens(lista_bytes_imagens):
    """Executa OCR local em uma lista de imagens (em bytes) e retorna o texto extraído."""
    if not lista_bytes_imagens:
        return ""

    if not TEM_RAPIDOCR:
        raise RuntimeError(
            "O documento contém imagens/páginas escaneadas, mas o motor de OCR não está instalado.\n"
            "Execute no terminal: python -m pip install --user rapidocr-onnxruntime"
        )

    motor_ocr = RapidOCR()
    textos_ocr = []

    for idx, img_bytes in enumerate(lista_bytes_imagens, start=1):
        resultado, _ = motor_ocr(img_bytes)
        if resultado:
            # Cada item de 'resultado' tem o formato: [coordenadas_caixa, texto_reconhecido, confianca]
            linhas = [item[1] for item in resultado if item[1].strip()]
            if linhas:
                textos_ocr.append(f"[Texto extraído por OCR - Imagem/Página {idx}]\n" + "\n".join(linhas))

    return "\n\n".join(textos_ocr).strip()


def ler_docx(caminho):
    """Extrai texto de parágrafos e tabelas de um .docx e aplica OCR em imagens embutidas, se necessário."""
    partes_texto = []

    if TEM_PYTHON_DOCX:
        doc = Document(caminho)
        for paragrafo in doc.paragraphs:
            if paragrafo.text.strip():
                partes_texto.append(paragrafo.text)

        for tabela in doc.tables:
            for linha in tabela.rows:
                celulas = [celula.text.strip() for celula in linha.cells if celula.text.strip()]
                if celulas:
                    partes_texto.append(" | ".join(celulas))
    else:
        # Fallback nativo via XML caso python-docx não esteja instalado
        with zipfile.ZipFile(caminho) as arquivo_zip:
            xml_conteudo = arquivo_zip.read("word/document.xml")

        arvore = ET.fromstring(xml_conteudo)
        namespace = {"w": "http://schemas.openxmlformats.org/wordprocessingml/2006/main"}
        corpo = arvore.find("w:body", namespace)

        if corpo is not None:
            for elemento in corpo:
                if elemento.tag.endswith("}p"):
                    textos = [t.text for t in elemento.findall(".//w:t", namespace) if t.text]
                    if textos:
                        partes_texto.append("".join(textos))
                elif elemento.tag.endswith("}tbl"):
                    for linha in elemento.findall(".//w:tr", namespace):
                        celulas = []
                        for celula in linha.findall(".//w:tc", namespace):
                            texto_celula = "".join(
                                t.text for t in celula.findall(".//w:t", namespace) if t.text
                            ).strip()
                            if texto_celula:
                                celulas.append(texto_celula)
                        if celulas:
                            partes_texto.append(" | ".join(celulas))

    texto_nativo = "\n".join(partes_texto).strip()

    # Verifica se há imagens embutidas dentro de word/media/
    imagens_bytes = []
    extensoes_validas = (".png", ".jpg", ".jpeg", ".bmp", ".tiff", ".webp")
    with zipfile.ZipFile(caminho) as arquivo_zip:
        for nome_interno in sorted(arquivo_zip.namelist()):
            if nome_interno.startswith("word/media/") and nome_interno.lower().endswith(extensoes_validas):
                imagens_bytes.append(arquivo_zip.read(nome_interno))

    # Se houver imagens e pouco texto nativo (ex: documento montado com prints) ou imagens complementares,
    # roda o OCR nas imagens caso o motor esteja disponível (ou obrigatoriamente se não houver texto nativo).
    texto_ocr = ""
    if imagens_bytes:
        if len(texto_nativo) < 100 or TEM_RAPIDOCR:
            print(f"Executando OCR local em {len(imagens_bytes)} imagem(ns) encontrada(s) no .docx...")
            texto_ocr = executar_ocr_em_imagens(imagens_bytes)

    return "\n\n".join(filter(None, [texto_nativo, texto_ocr])).strip()


def ler_pdf(caminho):
    """Extrai texto selecionável de cada página do PDF; aplica OCR nas páginas escaneadas (sem texto)."""
    if not TEM_PYMUPDF:
        raise RuntimeError(
            "Para ler arquivos .pdf, instale o PyMuPDF executando no terminal:\n"
            "python -m pip install --user pymupdf"
        )

    doc_pdf = fitz.open(caminho)
    partes_finais = []
    matriz_zoom = fitz.Matrix(200 / 72, 200 / 72)  # 200 DPI para alta precisão no OCR local

    for num_pagina, pagina in enumerate(doc_pdf, start=1):
        texto_pagina = pagina.get_text("text").strip()

        # Se a página tem texto selecionável suficiente, usa diretamente
        if len(texto_pagina) >= 50:
            partes_finais.append(f"--- Página {num_pagina} ---\n{texto_pagina}")
        else:
            # Página sem camada de texto (escaneada/imagem): renderiza em PNG e roda OCR local
            print(f"Página {num_pagina} sem texto selecionável detectada. Executando OCR local...")
            pix = pagina.get_pixmap(matrix=matriz_zoom)
            texto_ocr_pagina = executar_ocr_em_imagens([pix.tobytes("png")])
            if texto_ocr_pagina:
                partes_finais.append(f"--- Página {num_pagina} (OCR) ---\n{texto_ocr_pagina}")

    doc_pdf.close()
    return "\n\n".join(partes_finais).strip()


# 2. Identifica a extensão (.docx ou .pdf) e extrai todo o texto (nativo + OCR)
if not os.path.exists(CAMINHO_ARQUIVO):
    print(f"Erro: O arquivo '{CAMINHO_ARQUIVO}' não foi encontrado.")
    exit()

extensao = os.path.splitext(CAMINHO_ARQUIVO)[1].lower()

try:
    print(f"Processando arquivo: {os.path.basename(CAMINHO_ARQUIVO)}...")
    if extensao == ".docx":
        conteudo_arquivo = ler_docx(CAMINHO_ARQUIVO)
    elif extensao == ".pdf":
        conteudo_arquivo = ler_pdf(CAMINHO_ARQUIVO)
    else:
        print(f"Erro: Formato '{extensao}' não suportado. Utilize apenas arquivos .docx ou .pdf.")
        exit()

    if not conteudo_arquivo:
        print("Erro: Não foi possível extrair nenhum texto do documento (nem nativo, nem via OCR).")
        exit()

    print(f"Extração concluída: {len(conteudo_arquivo)} caracteres prontos para análise.")

except Exception as erro:
    print(f"Erro ao processar o arquivo: {erro}")
    exit()


# 3. Detecta automaticamente qual servidor Ollama está ativo e busca os modelos
url_base_ativa = None
modelos = []

print("\nProcurando servidor Ollama disponível...")

for servidor in SERVIDORES_OLLAMA:
    try:
        with urllib.request.urlopen(f"{servidor}/api/tags", timeout=3) as resposta_tags:
            dados_tags = json.loads(resposta_tags.read().decode("utf-8"))
            # Remove apenas modelos exclusivos de embedding (todos os demais aceitam texto puro)
            modelos = [
                modelo["name"]
                for modelo in dados_tags.get("models", [])
                if "embed" not in modelo["name"].lower()
            ]
            url_base_ativa = servidor
            print(f"Conectado com sucesso em: {url_base_ativa}\n")
            break
    except Exception:
        continue

if not url_base_ativa:
    print("Erro: Não foi possível conectar a nenhum servidor Ollama (nem local, nem IFBA).")
    print("Verifique sua conexão com a internet/rede ou se o serviço local está rodando.")
    exit()

if not modelos:
    print(f"O servidor {url_base_ativa} respondeu, mas não possui nenhum modelo de chat instalado.")
    exit()


# 4. Menu dinâmico para escolher a LLM
print("=== Escolha o modelo disponível ===")
for i, nome in enumerate(modelos, 1):
    print(f"{i}. {nome}")

while True:
    try:
        opcao = int(input("\nDigite o número do modelo: ")) - 1
        if 0 <= opcao < len(modelos):
            modelo_escolhido = modelos[opcao]
            break
        else:
            print(f"Por favor, digite um número entre 1 e {len(modelos)}.")
    except ValueError:
        print("Entrada inválida. Digite apenas o número correspondente ao modelo.")


# 5. Prompt de extração jurídica
mensagem = """Você é um auditor/analista jurídico. Leia o documento anexado e extraia o Proad (número do processo administrativo da auditoria), o Objeto da Auditoria (tema da auditoria) e o Objetivo (objetivo da auditoria).

Exemplo 1: Tecnologia da Informação
{
  "Proad": "1250/2025",
  "Objeto da Auditoria": "Processo de contratação e gestão de ativos de software e licenças corporativas.",
  "Objetivo": "Avaliar se o planejamento, a aquisição e o controle das licenças de software no Tribunal cumprem as normativas do CNJ e evitam desperdício de recursos."
}

Exemplo 2: Gestão de Pessoas
{
  "Proad": "2891/2025",
  "Objeto da Auditoria": "Concessão e pagamento de adicionais de qualificação e ações de capacitação.",
  "Objetivo": "Verificar se os critérios para concessão de adicionais e os investimentos em capacitação de servidores estão alinhados ao Plano Estratégico Institucional e à legislação vigente."
}

Exemplo 3: Sustentabilidade e Logística
{
  "Proad": "5612/2024",
  "Objeto da Auditoria": "Gestão de frota oficial, consumo de combustíveis e manutenção de veículos.",
  "Objetivo": "Analisar a eficiência do gasto público com o transporte institucional e avaliar a conformidade com as metas de sustentabilidade do Plano de Logística Sustentável (PLS)."
}

Primeiro pesquise o Proad, depois o objeto da auditoria, em seguida o objetivo e, por fim, gere a saída.
Limite-se a consultar apenas o documento anexado e não inclua informações que não estejam presentes no documento anexado. O formato de saída deve ser um arquivo json no próprio chat."""

prompt_final = f"""Analise o documento abaixo e responda à solicitação do usuário baseando-se estritamente no conteúdo fornecido.

=== CONTEÚDO DO ARQUIVO ===
{conteudo_arquivo}
=== FIM DO ARQUIVO ===

Solicitação do usuário: {mensagem}"""


# 6. Envia a requisição (sempre em texto puro, compatível com qualquer LLM de chat)
url_chat = f"{url_base_ativa}/api/chat"
dados = {
    "model": modelo_escolhido,
    "messages": [{"role": "user", "content": prompt_final}],
    "stream": True,
    "format": "json",
    "options": {
        "temperature": 0.1,
        "num_ctx": 8192
    }
}

requisicao = urllib.request.Request(
    url_chat,
    data=json.dumps(dados).encode("utf-8"),
    headers={"Content-Type": "application/json"}
)

print(f"\nEnviando documento para {modelo_escolhido} em {url_base_ativa}...")
print("Resposta da IA:\n")

try:
    with urllib.request.urlopen(requisicao, timeout=300) as resposta:
        for linha in resposta:
            if linha:
                pedaco = json.loads(linha.decode("utf-8"))
                conteudo = pedaco.get("message", {}).get("content", "")
                print(conteudo, end="", flush=True)
                if pedaco.get("done"):
                    break
    print("\n")
except Exception as erro:
    print(f"\nErro durante o processamento no servidor: {erro}")