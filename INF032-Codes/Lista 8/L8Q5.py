'''
5. Faça um programa que receba do usuário um arquivo texto e mostre na tela quantas vezes cada letra do alfabeto aparece dentro do arquivo.
'''

# Pré-definições
letter_a = "áàâã"
letter_e = "éèê"
letter_i = "íìî"
letter_o = "óòôõ"
letter_u = "úùû"
alfabeto = "abcdefghijklmnopqrstuvwxyz"

# Criando um dicionário
dictAlfabeto = {}
for letra in alfabeto:
    dictAlfabeto[letra] = 0

# Converte a letra para minúscula e a vogal para sem acento
def whichletter(char):
    
    char = char.lower()
    
    if char in letter_a:
        return "a"
    elif char in letter_e:
        return "e"
    elif char in letter_i:
        return "i"
    elif char in letter_o:
        return "o"
    elif char in letter_u:
        return "u"
    else:
        return char # Vogais sem acento ou consoantes

# Contabiliza as letras
def contarLetra(conteudo):
    for letra in conteudo:
        letra = whichletter(letra)
        if letra in dictAlfabeto:
            dictAlfabeto[letra] += 1   

caminho = input("Informe o caminho do arquivo: ")
caminho = caminho.replace("\\", "/")

try: 
    arquivo = open(caminho, "r")
    conteudo = arquivo.read()

    contarLetra(conteudo)
    print(dictAlfabeto)

    arquivo.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")