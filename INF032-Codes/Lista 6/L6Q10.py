'''
10.Criar um programa que deixe entrar com 10 números positivos e imprima a raiz quadrada de cada numero. para cada entrada de dados devera haver um trecho de "proteção" para que um numero negativo não seja aceito.
'''

import math

i = 0
while i < 10:

    numero = int(input(f"Informe um numero maior que 0 (cont: {i + 1}): "))
    
    if numero < 0:
        print("O numero nao pode ser negativo. Informe um numero positivo.\n")
        continue
    
    i += 1
    raiz = math.sqrt(numero)
    print(f"A raiz de {numero} e {raiz: .2f}.\n")
