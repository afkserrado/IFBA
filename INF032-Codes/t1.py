######################################################################
# Bibliotecas
from pathlib import Path # Biblioteca para manipulação de caminhos
from docx import Document # Biblioteca para manipulação de documentos no formato .docx

######################################################################
# Funções

# Armazena as subpastas de uma pasta em uma lista
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

def pegarResumos(dirSubpastas):
    dirResumos = []
    for dirSubpasta in dirSubpastas:
        itens = dirSubpasta.iterdir()
        for item in itens:
            if item.is_file() and item.name.upper().startswith("RESUMO") and item.suffix == ".docx":
                dirResumos.append(item)

    return dirResumos       

def lerResumos(dirResumos):
    docxResumos = []
    for dir in dirResumos:
        docxResumos.append(Document(dir))

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