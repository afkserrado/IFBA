'''
6. Entrar com 3 números e imprimi-los em ordem crescente.
'''

num1 = int(input("Informe o 1 numero: "))
num2 = int(input("Informe o 2 numero: "))
num3 = int(input("Informe o 3 numero: "))

if num1 > num2:
    num1, num2 = num2, num1
if num1 > num3:
    num1, num3 = num3, num1
if num2 > num3:
    num2, num3 = num3, num2

print(f"Ordem: {num1}, {num2}, {num3}.")
