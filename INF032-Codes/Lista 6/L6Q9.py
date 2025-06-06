'''
9.Uma empresa de fornecimento de energia elétrica faz a leitura mensal dos medidores de consumo. Para cada consumidor, são digitados os seguintes dados:

a)Numero do consumidor; 
b)Quantidade de kWh consumidos durante o mês; 
c)tipo do consumidor -> 1-residencial, preço em reias de kWh = 0,3 / 2-comercial, preço em reias de kWh = 0,5 / 3-industrial, preço em reias de kWh = 0,7. Os dados devem ser lidos ate que seja encontrado um consumidor com numero 0(zero). calcular e imprimir: a) o custo total para cada consumidor; b)o total de consumo para os 3(três) tipos de consumidor; c)a media de consumo dos tipos 1 e 2.
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

    for chave in tarifa:
        if chave == tipo:
            custo = consumo * tarifa[chave]

    lista = {"numero": numero, "consumo": consumo, "tipo": tipo, "tarifa": tarifa[tipo], "custo": custo}

    #Total para cada tipo
    if lista["tipo"] == 1:
        tt1 += lista["consumo"]
        cont1 += 1
    elif lista["tipo"] == 2:
        tt2 += lista["consumo"]
        cont2 += 1
    elif lista["tipo"] == 3:
        tt3 += lista["consumo"]

    print(f"{lista}")
    print(f"Custo total: {custo: .2f}\n")

print(f"Total de consumo do tipo 1: {tt1}.\n")
print(f"Total de consumo do tipo 2: {tt2}.\n")
print(f"Total de consumo do tipo 3: {tt3}.\n")

if cont1 > 0:
    print(f"Media de consumo do tipo 1: {tt1/cont1: .2f}")
else:
    print("Nenhum consumidor do tipo 1 registrado.")

if cont2 > 0:
    print(f"Media de consumo do tipo 2: {tt2/cont2: .2f}")
else:
    print("Nenhum consumidor do tipo 2 registrado.")