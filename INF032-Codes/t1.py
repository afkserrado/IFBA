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
def pegarSubpastas(cmPasta):
    cmSubpastas = []

    # Tenta acessar o caminho da pasta principal
    try:
        
        # Verifica se a pasta existe e se corresponde a um diretório
        if cmPasta.exists() and cmPasta.is_dir():
            
            # Guarda o caminho de todos os arquivos e subpastas
            itens = cmPasta.iterdir() 
            
            # Percorre todos os caminhos dentro da pasta principal
            for item in itens:
                
                # Tenta acessar o caminho de uma subpasta ou arquivo
                try:
                    
                    # Verifica se o caminho corresponde a uma pasta
                    if item.is_dir(): 
                        nome = item.name[:8] 
                        partes = nome.split()

                        # Verifica se os oito primeiros caracteres do nome da subpasta correspondem ao padrão AA MM DD
                        if len(partes) == 3 and all(parte.isdigit() for parte in partes):
                            cmSubpastas.append(item)

                # Em caso de qualquer erro, registra no log
                except Exception as erro:
                    logging.error(f"Erro ao acessar a subpasta {item}: {erro}")
                    continue
 
        else:
            print("O caminho não existe ou não é uma pasta.")
        
    # Em caso de qualquer erro, registra no log
    except Exception as erro:
        logging.error(f"Erro ao acessar a pasta principal: {erro}")

    return cmSubpastas

# Guarda o diretório dos resumos em uma lista
def pegarResumos(cmSubpastas):
    cmResumos = []

    for cmSubpasta in cmSubpastas:
        
        # Tenta acessar o caminho de uma subpasta
        try:
            itens = cmSubpasta.iterdir()

        # Em caso de qualquer erro, registra no log
        except Exception as erro:
            logging.error(f"Erro ao acessar {cmSubpasta}: {erro}")
            continue

        for item in itens:
            
            # Tenta acessar um caminho que está dentro de uma subpasta
            try:
                if item.is_file() and item.name.upper().startswith("RESUMO") and item.suffix == ".docx":
                    cmResumos.append(item)

            # Em caso de qualquer erro, registra no log
            except Exception as erro:
                logging.error(f"Erro ao acessar {item}: {erro}")
                continue

    return cmResumos       

# Abre os resumos
def lerResumos(cmResumos):
    docxResumos = []
    for dir in cmResumos:
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
cmPasta = home / "OneDrive" / "APS_DEMANDAS"

# Pega o caminho das subpastas dentro da pasta
cmSubpastas = pegarSubpastas(cmPasta)

# Verifica se a lista de subpastas está vazia
if not cmSubpastas:
    print("A pasta não contém subpastas.")

'''
for cmSubpasta in cmSubpastas:
    print(cmSubpasta.name)
'''

# Pega o caminho de um resumo dentro de uma subpasta
cmResumos = pegarResumos(cmSubpastas)

# Verifica se a lista de resumos está vazia
if not cmResumos:
    print("Não foram encontrados resumos.")

'''
for dirResumo in cmResumos:
    print(dirResumo.name)
'''

# Abre e lê todos os resumos encontrados
docxResumos = lerResumos(cmResumos)

for paragrafo in docxResumos[0].paragraphs:
    print(paragrafo.text)