'''
19. Fazer uma nova lista a partir de uma lista original composta de palavras. 

Lista = [['ontem','hoje','amanhã'],['sp','rj','mg','ce'],['são paulo','rio','santos','cuiabá']]

A nova lista deve ser construída usando for e append.

Lista_nova = ['ontem','hoje','amanhã','sp','rj','mg','ce','são paulo','rio','santos','cuiabá']

Após isso, deve-se:
(a) estender a lista original com a nova lista ['férias','negócios'].
(b) colocar a nova lista em ordem alfabética.
'''

lista = [['ontem','hoje','amanhã'],['sp','rj','mg','ce'],['são paulo','rio','santos','cuiabá']]
lista2 = []

# Separando os elementos da lista
for sublista in lista:
    for elemento in sublista:
        lista2.append(elemento)

# Lista nova
print(f"Lista nova: {lista2}")

# Estendendo a lista original
lista.append(['férias', 'negócios'])
print(f"a) Lista original modificada: {lista}")

# Ordenando a nova lista alfabeticamente
lista2.sort()
print(f"b) Lista nova: {lista2}")