'''
12. Entrar com dois números inteiros e efetuar a adição. Caso o valor somado seja maior que 20, este deverá ser apresentado somando-se a ele mais 8; caso o valor somado seja menor ou igual a 20, este deverá ser apresentado subtraindo-se 5.
'''

num1 = int(input("Informe um numero: "))
num2 = int(input("Informe outro numero: "))

soma = num1 + num2

if soma > 20: soma += 8
else: soma -= 5

print(f"O resultado final e: {soma}.")