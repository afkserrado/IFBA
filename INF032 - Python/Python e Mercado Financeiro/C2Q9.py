'''
 9. Fazer um algoritmo em Python que leia uma quantidade n de valores numéricos fornecidos pelo usuário por meio do input. O programa deve contar quantos pares e quantos ímpares existem e imprimir a soma com o PRINT( ).
'''

# Entrada de dados
n = int(input("Informe uma quantidade n de números: "))

# Inicializações
par = 0
imp = 0
sp = 0
si = 0

for i in range(0, n):
    num = int(input(f"Informe o número {i + 1}: "))
    
    if num % 2 == 0:
        par += 1
        sp += num
    else:
        imp += 1
        si += num

print(f"Foram informados {par} números pares, cuja soma total é {sp}, e {imp} números ímpares, cuja soma total é {si}.")