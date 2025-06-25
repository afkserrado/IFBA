'''
14. Fazer um algoritmo em Python usando while para percorrer uma lista e parar a busca por um elemento assim que encontrar sua aparição pela primeira vez. Imprimir a posição (índice que ocupa na lista) em que esse elemento se encontra. Por exemplo, para a lista a seguir:
'''

# Entrada de dados
busca = input("Informe a ação buscada: ")

lista = ['bbdc4', 'itub4', 'petr4', 'petr4', 'bbas3', 'petr4', 'sanb4',  'petr4', 'bpac3', 'petr4']
tam = len(lista) # 10

i = 0
while i < tam:
    if lista[i] == busca:
        break

    i += 1

print(f"A ação {busca} aparece pela primeira vez no índice {i}.")
