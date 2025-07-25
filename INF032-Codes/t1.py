######################################################################
# Bibliotecas
from pathlib import Path # Biblioteca para manipulação de caminhos

######################################################################
# Funções

# Armazena as subpastas de uma pasta em uma lista
def pegaSubpastas(pasta):
    
    # Verifica se a pasta existe e se corresponde a um diretório
    if pasta.exists() and pasta.is_dir():
        itens = pasta.iterdir() # Guarda o caminho de todos os arquivos e subpastas
        
        # Percorre todos os caminhos dentro de pasta
        subpastas = []
        for item in itens:
            if item.is_dir(): # Verifica se o caminho é um diretório
                nome = item.name[:8] 
                partes = nome.split() 

                # Verifica se os oito primeiros caracteres do nome da subpasta correspondem ao padrão AA MM DD
                if len(partes) == 3 and all(parte.isdigit() for parte in partes):
                    subpastas.append(item)

        return subpastas

    else:
        print("O caminho não existe ou não é uma pasta.")
        return []

######################################################################
# Main

# Cria um objeto (home) da classe Path para armazenar o diretório do usuário atual
home = Path.home() # Por exemplo C:\Users\fulano
pasta = home / "OneDrive" / "APS_DEMANDAS"

subpastas = pegaSubpastas(pasta)

# Verifica se a lista de subpastas está vazia
if not subpastas:
    print("A pasta não contém subpastas.")

'''
for subpasta in subpastas:
    print(subpasta.name)
'''


