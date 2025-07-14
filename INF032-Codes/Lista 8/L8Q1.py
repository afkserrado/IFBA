'''
1. Faça um programa que receba do usuário um arquivo texto e mostre na tela quantas linhas esse arquivo possui.
'''

caminho = input("Informe o caminho do arquivo texto: ")

# Substitui a \ por / (no windows, os caminhos utilizam \)
caminho = caminho.replace("\\", "/") 

# Obtém o arquivo
arquivo = open(caminho, "r")

# Obtém o conteúdo do arquivo
conteudo = arquivo.readlines()

# Quantidade de linhas (elementos da lista)
tam = len(conteudo)
print(f"Total de linhas: {tam}")

arquivo.close()



