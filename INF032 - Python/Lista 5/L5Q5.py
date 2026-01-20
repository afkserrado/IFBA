'''
5.Entrar com 3 números, e imprimir o maior número.
'''

num1 = int(input("Informe o 1 numero: "))
num2 = int(input("Informe o 2 numero: "))
num3 = int(input("Informe o 3 numero: "))
maior = num1

if num2 > maior:
    maior = num2
if num3 > maior:
    maior = num3

print(f"O maior numero e {maior}.")

