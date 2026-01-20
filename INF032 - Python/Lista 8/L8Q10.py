'''
10. Faça um programa no qual o usuario informa o nome do arquivo e uma palavra, e retorne
o numero de vezes que aquela palavra aparece no arquivo.
'''
import os

try:
    
    nomeArq = input("Informe o nome do arquivo: ")
    palavra = input("Informe a palavra buscada: ")
    palavra = palavra.lower()

    arquivo = open(nomeArq, "r")
    conteudo = arquivo.read().lower()

    cont = conteudo.count(palavra)
    print(f"A palavra {palavra} aparece {cont} vezes no arquivo.")

    arquivo.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")