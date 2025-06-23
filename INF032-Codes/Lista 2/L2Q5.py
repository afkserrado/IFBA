'''
Lista de exercícios 2
5. Calcular a quantidade de litros de combustível gastos em uma viagem, sabendo-se que o carro faz 12km
com 1 litro. Deverão. Ser fornecidos a) tempo gasto na viagem; b) e a velocidade media. Apresentar os
valores da velocidade media, tempo gasto, distancia percorrida e quatidade de litros gastos.
'''

t = float(input("Informe o tempo de viagem: "))
vm = float(input("Informe a velocidade média: "))

d = vm * t
gasolina = d / 12

print("Tempo gasto = {}; Velocidade média = {}; Distância = {:.2f}; Gasolina = {:.2f}".format(t, vm, d, gasolina))