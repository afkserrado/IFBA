'''
12. Fazer um algoritmo usando while para encontrar a soma de n termos para a série a seguir:

s = 1 + 3/2 + 5/3 + 7/4 + 9/5 + ...

O usuário deve fornecer o número de termos desejado usando input, e o resultado deve ser impresso no Console com PRINT( ).
'''

# Entrada
n = int(input("Informe o número de termos da série: "))

# Inicializações
s = 0
numerador = 1
denominador = 1

# Cálculo da série
i = 0
while i < n:
    s += numerador / denominador

    # Incrementos
    numerador += 2
    denominador += 1
    i += 1

print(f"Soma = {s:.2f}.")