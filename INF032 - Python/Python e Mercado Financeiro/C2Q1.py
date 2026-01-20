'''
1. Fazer algoritmo e programa em Python: o usuário entra com um número usando input e o programa imprime com PRINT( ), se o número é positivo ou negativo.
'''

num = int(input("Informe um número: "))
if num < 0:
    print(f"O número {num} é negativo.")
elif num > 0:
    print(f"O número {num} é positivo.")
else:
    print(f"O número {num} é neutro.")