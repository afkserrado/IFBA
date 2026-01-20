'''
11) João Papo-de-Pescador, homem de bem, comprou um microcomputador para controlar o rendimento diário de seu trabalho. Toda vez que ele traz um peso de peixes maior que o estabelecido pelo regulamento de pesca do estado de São Paulo (50 quilos) deve pagar uma multa de R$ 4,00 por quilo excedente. João precisa que você faça um programa que leia a variável peso (peso de peixes) e calcule o excesso. Gravar na variável excesso a quantidade de quilos além do limite e na variável multa o valor da multa que João deverá pagar. Imprima os dados do programa com as mensagens
adequadas.

OK
'''

peso = 0
while peso != -1:
    peso = float(input("Informe o peso dos peixes (digite -1 para parar): "))
    excesso = 0
    multa = 0

    if peso == -1:
        break

    if peso > 50:
        excesso = peso - 50
        multa = excesso * 4

    print(f"Peso informado:{peso: .2f} | Excedente:{excesso: .2f} | Multa:{multa: .2f}\n")

print("Programa encerrado.")