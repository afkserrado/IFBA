'''
7. Entrar com 3 números e armazená-los em três variáveis diferentes:
maior, menor e intermediário.
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

menor = num1
intermediario = num2
maior = num3

print(f"Menor: {menor} | Intermediario: {intermediario} | Maior: {maior}.")