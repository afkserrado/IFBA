'''
11. Abra um arquivo texto, calcule e escreva o numero de caracteres, o numero de linhas e o numero de palavras neste arquivo. Escreva também quantas vezes cada letra ocorre no arquivo (ignorando letras com acento). Obs.: palavras são separadas por um ou mais caracteres espaco, tabulação ou nova linha.
'''

# Pré-definições
letra_a = "áàâã"
letra_e = "éèê"
letra_i = "íìî"
letra_o = "óòôõ"
letra_u = "úùû"
alfabeto = "abcdefghijklmnopqrstuvwxyz"

# Criando um dicionário
dictAlfabeto = {}
for letra in alfabeto:
    dictAlfabeto[letra] = 0

# Converte a letra para minúscula e a vogal para sem acento
def whichletra(letra):
    letra = letra.lower()
    if letra in letra_a:
        return "a"
    elif letra in letra_e:
        return "e"
    elif letra in letra_i:
        return "i"
    elif letra in letra_o:
        return "o"
    elif letra in letra_u:
        return "u"
    else:
        return letra # Vogais sem acento ou consoantes

# Contabiliza as letras
def contarLetras(conteudo):
    for letra in conteudo:
        letra = whichletra(letra)
        if letra in dictAlfabeto:
            dictAlfabeto[letra] += 1  

try: 
    nomeArq = input("Informe o nome do arquivo: ")
    with open(nomeArq, "r") as arq:
        conteudo = arq.read()
    
    linhas = conteudo.count("\n")
    caracteres = len(conteudo)
    palavras = len(conteudo.split())
    contarLetras(conteudo)

    with open(nomeArq, "a") as arq:
        arq.write("\n")
        arq.write(f"Caracteres = {caracteres}  | Linhas = {linhas} | Palavras = {palavras}")
        arq.write("\nContagem de letras:")
        for chave, valor in dictAlfabeto.items():
            arq.write(f" {chave}:{valor}")

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")