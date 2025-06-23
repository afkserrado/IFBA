'''
8.Chico tem 1.50m e cresce 2 centímetros por ano, enquanto Juca tem 1.10m e cresce 3 cm por ano. construir um programa que calcule e imprima quantos anos serão necessários para Juca seja maior que Chico.

OK
'''

hChico = 1.5
hJuca = 1.1
txChico = 0.02
txJuca = 0.03

anos = 0

while hJuca <= hChico:
    hChico += txChico
    hJuca += txJuca

    anos += 1

print(f"Juca ultrapassara a altura de Chico em {anos} anos.")