'''
6. Faça um programa que receba do usuário um arquivo texto. Crie outro arquivo texto contendo o texto do arquivo de entrada, mas com as vogais substituídas por '*'.
'''

# Vogais
vogais = "aáàâãeéèêiíìîoóòôõuúùû"

caminho = input("Informe o caminho do arquivo: ")
caminho = caminho.replace("\\", "/")

try: 
    arquivo = open(caminho, "r", encoding="utf-8")
    conteudo = arquivo.read()

    conteudo2 = ""
    for char in conteudo:
        char2 = char.lower()
        if char2 in vogais:
            conteudo2 += "*"
        else:
            conteudo2 += char

    arquivo2 = open("L8Q6_saida.txt", "w", encoding="utf-8")
    arquivo2.write(conteudo2)

    arquivo.close()
    arquivo2.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")