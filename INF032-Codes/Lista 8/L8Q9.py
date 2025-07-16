'''
9. Faça um programa que receba como entrada o nome de um arquivo de entrada e outro de saída. O arquivo de entrada contém em cada linha o nome de uma cidade (ocupando 40 caracteres) e o seu numero de habitantes. O programa deverá ler o arquivo de entrada e gerar um arquivo de saída onde aparece o nome da cidade mais populosa seguida pelo seu numero de habitantes.
'''

# Obtém os caminhos
entrada = input("Informe o nome do arquivo de entrada: ")
saida = input("Informe o nome do arquivo de saída: ")

try: 
    # Abrindo arquivos
    arqEntrada = open(entrada, "r", encoding="utf-8")
    arqSaida = open(saida, "w", encoding="utf-8")

    dadosEntrada = arqEntrada.readlines()
    
    # Cria um dicionário com os dados
    cidades = {}
    for linha in dadosEntrada:
        cidades[linha[:40]] = int(linha[40:].strip().replace(".",''))

    # Obtém a população máxima
    popMax = max(cidades.values())

    for chave, valor in cidades.items():
        if valor == popMax:
            popFormatada = f"{popMax:,}".replace(",",".")
            resultado = chave + popFormatada + "\n"
            arqSaida.write(resultado)
            break

    # Fechando arquivos
    arqEntrada.close()
    arqSaida.close()
    
# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")