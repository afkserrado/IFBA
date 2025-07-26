######################################################################
# Bibliotecas
from pathlib import Path # Biblioteca para manipulação de caminhos
from docx import Document # Biblioteca para manipulação de documentos no formato .docx
import logging

# Configuração básica do logging
logging.basicConfig(
    # Nome do arquivo de log
    filename = 'erros_resumos.log',  
    
    # 'a' para acrescentar ao final da linha
    filemode = 'a', 
    
    # Data e hora - Nível do log - Mensagem do log
    format = '%(asctime)s - %(levelname)s - %(message)s', 
    
    # Define o nível mínimo de severidade do erro para ser registrado no arquivo
    level = logging.ERROR # Registra apenas erros do tipo ERROR e CRITICAL
)

######################################################################
# Funções

# Guarda o diretório das subpastas de uma pasta em uma lista
def pegarSubpastas(dirPasta):
    
    # Verifica se a pasta existe e se corresponde a um diretório
    if dirPasta.exists() and dirPasta.is_dir():
        itens = dirPasta.iterdir() # Guarda o caminho de todos os arquivos e subpastas
        
        # Percorre todos os caminhos dentro de pasta
        dirSubpastas = []
        for item in itens:
            if item.is_dir(): # Verifica se o caminho é um diretório
                nome = item.name[:8] 
                partes = nome.split()

                # Verifica se os oito primeiros caracteres do nome da subpasta correspondem ao padrão AA MM DD
                if len(partes) == 3 and all(parte.isdigit() for parte in partes):
                    dirSubpastas.append(item)

        return dirSubpastas

    else:
        print("O caminho não existe ou não é uma pasta.")
        return []

# Guarda o diretório dos resumos em uma lista
def pegarResumos(dirSubpastas):
    dirResumos = []
    for dirSubpasta in dirSubpastas:
        itens = dirSubpasta.iterdir()
        for item in itens:
            if item.is_file() and item.name.upper().startswith("RESUMO") and item.suffix == ".docx":
                dirResumos.append(item)

    return dirResumos       

# Abre os resumos
def lerResumos(dirResumos):
    docxResumos = []
    for dir in dirResumos:
        try:
            docxResumos.append(Document(dir))
        except Exception as erro:
            # Registra a mensagem de erro no arquivo de log
            logging.error(f"Erro ao abrir {dir.name}: {erro}")
            continue

    return docxResumos

######################################################################
# Main

# Cria um objeto (home) da classe Path para armazenar o diretório do usuário atual
home = Path.home() # Por exemplo C:\Users\fulano
dirPasta = home / "OneDrive" / "APS_DEMANDAS"

# Pega o caminho das subpastas dentro da pasta
dirSubpastas = pegarSubpastas(dirPasta)

# Verifica se a lista de subpastas está vazia
if not dirSubpastas:
    print("A pasta não contém subpastas.")

'''
for dirSubpasta in dirSubpastas:
    print(dirSubpasta.name)
'''

# Pega o caminho de um resumo dentro de uma subpasta
dirResumos = pegarResumos(dirSubpastas)

# Verifica se a lista de resumos está vazia
if not dirResumos:
    print("Não foram encontrados resumos.")

'''
for dirResumo in dirResumos:
    print(dirResumo.name)
'''

# Abre e lê todos os resumos encontrados
docxResumos = lerResumos(dirResumos)

for paragrafo in docxResumos[0].paragraphs:
    print(paragrafo.text)