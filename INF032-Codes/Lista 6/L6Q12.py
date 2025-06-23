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
    area = float(input("Informe a área (digite 0 ou menor para parar): "))

    if area <= 0:
        break

    litros = area / 6

    # Solução 1: apenas latas
    qLatas = math.ceil(litros / 18)
    precoLatas = qLatas * 80
    print(f"Apenas latas -> Quantidade: {qLatas} | Custo: R$ {precoLatas:.2f}")

    # Solução 2: apenas galões
    qGaloes = math.ceil(litros / 3.6)
    precoGaloes = qGaloes * 25
    print(f"Apenas galões -> Quantidade: {qGaloes} | Custo: R$ {precoGaloes:.2f}")

    # Solução 3: mistura
    if litros <= 10.8:
        qLatasMix = 0
        qGaloesMix = math.ceil(litros / 3.6)
    else:
        qLatasMix = math.floor(litros / 18)
        if qLatasMix == 0:
            qLatasMix = 1
        sobra = litros - (qLatasMix * 18)

        if sobra > 0:
            qGaloesMix = math.ceil(sobra / 3.6)
        else:
            qGaloesMix = 0

    precoMix = (qLatasMix * 80) + (qGaloesMix * 25)
    print(f"Combinação -> Latas: {qLatasMix} | Galões: {qGaloesMix} | Custo Total: R$ {precoMix:.2f}\n")

print("Programa encerrado.")