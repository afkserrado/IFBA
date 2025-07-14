'''
3. Faça um programa que receba do usuário um arquivo texto e mostre na tela quantas letras são vogais e quantas são consoantes.
'''

import string

# Retorna todas as letras minúsculas do alfabeto
#alfabeto = string.ascii_lowercase

# Verifica se um caractere é vogal
def isvowel(char):
    return char.lower() in "aáàâãeéèêiíìîoóòôõuúùû"

# Verifica se um caractere é consoante
def isconsonant(char):
    return char.lower() in "bcdfghjklmnpqrstvwxyz"
    #return char.lower() in consoantes

'''   
# Constrói a lista de consoantes
def listarConsoantes(consoantes):
    for letra in alfabeto:
            if not isvowel(letra):
                consoantes.append(letra)


# Consoantes
consoantes = []
listarConsoantes(consoantes)
'''
   
caminho = input("Informe o caminho do arquivo: ")
caminho = caminho.replace("\\", "/")

try: 
    arquivo = open(caminho, "r")
    conteudo = arquivo.read()

    qtdVogais = 0
    qtdConsoante = 0
    for char in conteudo:
        if (isvowel(char)):
            qtdVogais += 1
        elif (isconsonant(char)):
            qtdConsoante += 1

    print(f"O arquivo tem {qtdVogais} vogais e {qtdConsoante} consoantes.")

    arquivo.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")

