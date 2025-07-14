'''
2. Faça um programa que receba do usuário um arquivo texto e mostre na tela quantas letras são vogais.
'''

# Verifica se um caractere é vogal
def isvowel(char):
    return char.lower() in "aáàâãeéèêiíìîoóòôõuúùû"

caminho = input("Informe o caminho do arquivo: ")
caminho = caminho.replace("\\", "/")

try: 
    arquivo = open(caminho, "r")
    conteudo = arquivo.read()

    vogais = 0
    for char in conteudo:
        if (isvowel(char)):
            vogais += 1

    print(f"Quantidade de vogais no arquivo: {vogais}")

    arquivo.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")

