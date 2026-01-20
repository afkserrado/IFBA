'''
18. Muitas vezes, temos listas cujos elementos são listas e desejamos que os elementos desses elementos formem uma nova lista. Fazer um algoritmo em Python que 
tome uma lista de listas, por exemplo: Lista = [ [1,2,-1] , [3,-1,4,5] , [0,0,1,2,-1] ,[-1,-1,2,2,-1,2,-1] , [3,2,0] , [1,1,-1,0,2] ], e forme uma nova lista usando for e append.

Lista_nova = [1, 2, -1, 3, -1, 4, 5, 0, 0, 1, 2, -1, -1, -1, 2, 2, -1, 2, -1, 3, 2, 0, 1, 1, -1, 0, 2].

Com essa nova lista fatiada da lista original, fazer com que seja apresentado no Console usando o comando PRINT():

(a) A nova lista.
(b) A soma dos elementos dessa nova lista.
(c) O maior elemento dessa nova lista.
(d) O menor elemento dessa nova lista.
(e) A média dos elementos dessa nova lista.
(f) A moda dos elementos dessa nova lista.
(g) O desvio-padrão populacional dessa nova lista.
'''

from statistics import mean, mode, pstdev

lista = [[1,2,-1], [3,-1,4,5], [0,0,1,2,-1], [-1,-1,2,2,-1,2,-1], [3,2,0], [1,1,-1,0,2]]
lista2 = []

for sublista in lista:
    for elemento in sublista:
        lista2.append(elemento)

print(f"a) Lista nova: {lista2}")
print(f"b) Soma: {sum(lista2)}")
print(f"c) Maior: {max(lista2)}")
print(f"d) Menor: {min(lista2)}")
print(f"e) Média: {mean(lista2)}")
print(f"f) Moda: {mode(lista2)}")
print(f"g) Desvio-padrão populacional: {pstdev(lista2)}")