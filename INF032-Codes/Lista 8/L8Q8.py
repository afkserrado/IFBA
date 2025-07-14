'''
8. Faça um programa que receba dois arquivos do usuário, e crie um terceiro arquivo com o conteúdo dos dois primeiros juntos (o conteúdo do primeiro seguido do conteúdo do segundo).
'''

# Obtém os caminhos
caminhos = []
for i in range(3):
    caminho = input(f"Informe o caminho do arquivo {i + 1}: ")
    caminho = caminho.replace("\\", "/")
    caminhos.append(caminho)

try: 
    
    # Guarda os arquivos de entrada e seus conteúdos
    arquivos = []
    conteudos = []
    for i in range(2):
        arq = open(caminhos[i], "r", encoding="utf-8")
        arquivos.append(arq)
        conteudo = arq.read()
        conteudos.append(conteudo)

    arqSaida = open(caminhos[2], "w", encoding="utf-8")
    arquivos.append(arqSaida)
    for conteudo in conteudos:
        arqSaida.write(conteudo)
        arqSaida.write("\n")
    
    for arq in arquivos:
        arq.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")