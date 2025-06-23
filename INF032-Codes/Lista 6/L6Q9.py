'''
9.Uma empresa de fornecimento de energia elétrica faz a leitura mensal dos medidores de consumo. Para cada consumidor, são digitados os seguintes dados:

a)Numero do consumidor; 
b)Quantidade de kWh consumidos durante o mês; 
c)tipo do consumidor -> 1-residencial, preço em reias de kWh = 0,3 / 2-comercial, preço em reias de kWh = 0,5 / 3-industrial, preço em reias de kWh = 0,7. Os dados devem ser lidos ate que seja encontrado um consumidor com numero 0(zero). calcular e imprimir: a) o custo total para cada consumidor; b)o total de consumo para os 3(três) tipos de consumidor; c)a media de consumo dos tipos 1 e 2.

OK
'''

numero = 1
tarifa = {1: 0.3, 2: 0.5, 3: 0.7}
tt1 = tt2 = tt3 = 0
cont1 = cont2 = 0

while numero != 0:

    numero = int(input("Informe um numero: "))

    if numero == 0:
        print("Programa encerrado.")
        break

    consumo = int(input("Informe o consumo em kWh: "))
    tipo = int(input("Informe o tipo do consumidor (1 - residencial; 2 - comercial; 3 - industrial): "))

    if tipo not in [1,2,3]:
        print("Tipo invalido. Informe um numero de 1 a 3.\n")
        continue

    custo = consumo * tarifa[tipo]

    #Total para cada tipo
    if tipo == 1:
        tt1 += consumo
        cont1 += 1
    elif tipo == 2:
        tt2 += consumo
        cont2 += 1
    elif tipo == 3:
        tt3 += consumo

    print(f"Custo total: R$ {custo: .2f}\n")

print(f"Total de consumo do tipo 1: {tt1} kWh.\n")
print(f"Total de consumo do tipo 2: {tt2} kWh.\n")
print(f"Total de consumo do tipo 3: {tt3} kWh.\n")

if cont1 > 0:
    print(f"Media de consumo do tipo 1: {tt1/cont1: .2f} kWh.")
else:
    print("Nenhum consumidor do tipo 1 registrado.")

if cont2 > 0:
    print(f"Media de consumo do tipo 2: {tt2/cont2: .2f} kWh.")
else:
    print("Nenhum consumidor do tipo 2 registrado.")