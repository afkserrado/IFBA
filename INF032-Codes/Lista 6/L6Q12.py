'''
12) Faça um Programa para uma loja de tintas. O programa deverá pedir o tamanho em metros quadrados da área a ser pintada.
Considere que a cobertura da tinta é de 1 litro para cada 6 metros quadrados e que a tinta é vendida em latas de 18 litros, que custam R$ 80,00 ou em galões de 3,6 litros, que custam R$ 25,00.

• Informe ao usuário as quantidades de tinta a serem compradas e os respectivos preço em 3 situações:
• comprar apenas latas de 18 litros;
• comprar apenas galões de 3,6 litros;
• misturar latas e galões, de forma que o preço seja o menor. 

OK
'''

import math

area = 1
while area > 0:

    area = float(input("Informe a area (digite 0 ou menor para parar): "))

    if area <= 0:
        break

    litros = area / 6
    
    # Solução 1
    qLatas = math.ceil(litros / 18)
    pLatas = qLatas * 80
    print(f"Apenas latas -> Quantidade: {qLatas} | Custo: {pLatas: .2f}")

    # Solução 2
    qGaloes = math.ceil(litros / 3.6)
    pGaloes = qGaloes * 25
    print(f"Apenas galoes -> Quantidade: {qGaloes} | Custo: {pGaloes: .2f}")

    # Galões só é vantagem se for até 10,8 litros, que dá 3 galões, custando 75 reais. Acima disso, é mais vantajoso comprar uma lata.

    # Solução 3
    qLatas = math.ceil(litros / 18)
    resto = litros - (qLatas * 18)
    qGaloes = 0
    pGaloes = 0

    if resto > 0:
        qGaloes = math.ceil(resto / 3.6)

    pLatas = qLatas * 80
    pGaloes = qGaloes * 25

    print(f"Latas e galoes -> Quantidade latas: {qLatas} | Custo latas: {pLatas: .2f} | Quantidade galoes: {qGaloes} | Preco galoes: {pGaloes: .2f}\n")

print("Programa encerrado.")