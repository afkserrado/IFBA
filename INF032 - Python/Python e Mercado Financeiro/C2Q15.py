'''
15. Uma lista tem elementos repetidos. Fazer um algoritmo em Python usando 
while para percorrer essa lista e parar a busca por um elemento assim que 
ele aparecer pela segunda vez. Imprimir a posição (índice que ocupa na lista) desse elemento. Por exemplo, para a lista a seguir, se procuramos a sigla petr4:
'''

# Entrada de dados
busca = input("Informe a ação buscada: ")

lista = ['bbdc4', 'itub4', 'petr4', 'petr4', 'bbas3', 'petr4', 'sanb4',  'petr4', 'bpac3', 'petr4']
tam = len(lista) # 10

i = 0
cont = 0
while i < tam:
    if lista[i] == busca:
        cont += 1

        if cont == 2:
            print(f"A ação {busca} aparece pela segunda vez no índice {i}.")
            break

    i += 1
