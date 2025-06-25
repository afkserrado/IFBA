'''
10. A área abaixo de uma função matemática fornece alguns resultados importantes em termos de demanda, custo, lucro marginal, entre outros resultados na área de finanças. A integral seguinte não pode ser resolvida utilizando as técnicas usuais de cálculo diferencial e integral. Mas, pela aproximação da integral em n termos da série seguinte, tem-se uma boa aproximação do seu valor exato.
'''

from math import factorial

# Entrada de dados
n = int(input("Informe a quantidade de termos da série: "))
x = float(input("Informe o valor de x da série: "))

# Inicializações
s = 0
exp = 1 # O expoente começa em 1 e incrementa de 2 em 2 (1, 3, 5, 7...)

# Cálculo da série
for i in range(n):
    termo = (x ** exp) / (exp * factorial(i))

    if i % 2 == 0:
        s += termo
    else:
        s -= termo

    exp += 2

print(f"A soma é {s:.2f}.")