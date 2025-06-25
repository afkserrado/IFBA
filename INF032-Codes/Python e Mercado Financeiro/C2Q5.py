'''
5. Considere uma equação do segundo grau:
ax^2 + bx + c =0

Utilizando-se de Δ = b^2 - 4ac, escrever um programa em Python que calcule as raízes da equação tal que:

(i) Se não há raízes (Δ < 0), o programa retorna um PRINT “não existem raízes reais”.
(ii) Se há uma única raiz (Δ = 0), o programa mostra ao usuário a única raiz calculada como: x1 = −b / (2*a).
(iii) Se há duas raízes, mostre ao usuário calculando-as desta forma:
x1 = (-b + Δ^(1/2))/2a e x2 = (-b - Δ^(1/2))/2a
'''

from math import sqrt

# Entrada de dados
a = float(input("Informe a: "))
b = float(input("Informe b: "))
c = float(input("Informe c: "))

# Cálculo de delta
d = b ** 2 - 4 * a * c

print()
# Cálculo das raízes
if d < 0:
    print("Não há raízes reais para a equação.")
elif d == 0:
    x1 = -b / (2 * a)
    print(f"A equação possui uma única raiz x1 = {x1:.2f}.")
else:
    x1 = (-b + sqrt(d)) / (2 * a)
    x2 = (-b - sqrt(d)) / (2 * a)
    print(f"A equação possui duas raízes: x1 = {x1:.2f} e x2 = {x2:.2f}.")