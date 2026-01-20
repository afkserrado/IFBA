'''
7) Crie uma função que permita contar o número de vezes que aparece uma letra em uma string.

Obs: não precisa ser case sensitive.
'''

# Conta a quantidade de vezes que uma letra aparece na string
def contLetra(string, busca):
    string = string.lower()
    busca = busca.lower()
    qtd = 0
    for letra in string:
        if letra == busca:
            qtd += 1
    
    return qtd

# Main
string = input("Digite um texto: ")
letra = input("Informe uma letra: ")
letra = letra[0] # Garante que apenas uma letra seja considerada

qtd = contLetra(string, letra)
print(f"A letra '{letra}' aparece {qtd} vezes no texto '{string}'.")