import json
import urllib.request
from docx import Document

# ==========================================================
# Caminho completo do seu arquivo Word (.docx)
CAMINHO_ARQUIVO = r"C:\Users\aluno.ssa\Downloads\T1-TÓPICOS\1.2. Entendimento do objeto.docx"
# ==========================================================

# 1. Lê o conteúdo real do arquivo .docx (parágrafos e tabelas)
try:
    doc = Document(CAMINHO_ARQUIVO)
    partes_texto = []

    for paragrafo in doc.paragraphs:
        if paragrafo.text.strip():
            partes_texto.append(paragrafo.text)

    for tabela in doc.tables:
        for linha in tabela.rows:
            celulas = [celula.text.strip() for celula in linha.cells if celula.text.strip()]
            if celulas:
                partes_texto.append(" | ".join(celulas))

    conteudo_arquivo = "\n".join(partes_texto)

except FileNotFoundError:
    print(f"Erro: O arquivo '{CAMINHO_ARQUIVO}' não foi encontrado.")
    exit()
except Exception as erro:
    print(f"Erro ao ler o arquivo .docx: {erro}")
    exit()

# 2. Lista com os modelos instalados
modelos = [
    "llama3.2:latest",
    "llama3.1:8b",
    "qwen2.5-coder:7b",
    "gemma2:9b"
]

# 3. Menu simples para escolher a LLM
print("=== Escolha o modelo ===")
for i, nome in enumerate(modelos, 1):
    print(f"{i}. {nome}")

opcao = int(input("\nDigite o número do modelo: ")) - 1
modelo_escolhido = modelos[opcao]

# 4. Prompt sem o 'f' antes das aspas e com aspas duplas limpas no JSON
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

# 5. Une o conteúdo do arquivo com a sua pergunta
prompt_final = f"""Analise o documento abaixo e responda à solicitação do usuário baseando-se estritamente no conteúdo fornecido.

=== CONTEÚDO DO ARQUIVO ===
{conteudo_arquivo}
=== FIM DO ARQUIVO ===

Solicitação do usuário: {mensagem}"""

# 6. Prepara e envia a requisição para a API local do Ollama
url = "http://localhost:11434/api/chat"
dados = {
    "model": modelo_escolhido,
    "messages": [{"role": "user", "content": prompt_final}],
    "stream": False,
    "format": "json",  # Força o Ollama a responder estritamente em formato JSON válido
    "options": {
        "temperature": 0.1,  # Temperatura bem baixa para extração fiel de dados
        "num_ctx": 16384     # Memória ampliada para documentos de auditoria extensos
    }
}

requisicao = urllib.request.Request(
    url,
    data=json.dumps(dados).encode("utf-8"),
    headers={"Content-Type": "application/json"}
)

print("\nLendo documento e aguardando resposta...\n")

with urllib.request.urlopen(requisicao) as resposta:
    resultado = json.loads(resposta.read().decode("utf-8"))
    print("Resposta da IA:")
    print(resultado["message"]["content"])
