'''
16. É bastante comum se ter uma lista cujos elementos também são listas. Por exemplo: Palavras = [['comprar','vender'],['manter','alertar','indicar'],['tendencia','crash','lucro']].

Nessa lista, o primeiro elemento da lista Palavras é ['comprar','vender'], o segundo é ['manter','alertar','indicar'] e o terceiro, ['tendencia','crash','lucro']. Utilizar a noção de for para imprimir esses elementos-listas de forma separada.
'''

palavras = [['comprar','vender'],['manter','alertar','indicar'],['tendencia','crash','lucro']]

tam1 = len(palavras) # Número de sublistas
for i in range(tam1):
    lista = palavras[i]
    tam2 = len(lista) # Número de elementos dentro da sublista
    for j in range(tam2):
        # Imprime o elemento
        print(lista[j], end = '')

        # Imprime a vírgula, exceto após o último elemento
        if j < tam2 - 1:
            print(", ", end = '')
    
    # Imprime a vírgula, exceto após a última sublista
    if i < tam1 - 1:
        print(", ", end = '')