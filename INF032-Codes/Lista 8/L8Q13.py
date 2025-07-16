'''
13. Dado um arquivo contendo um conjunto de nome e data de nascimento (DD MM AAAA, isto é, 3 inteiros em sequencia), faca um programa que leia o nome do arquivo e a data de hoje e construa outro arquivo contendo o nome e a idade de cada pessoa do primeiro arquivo.
'''

def calcularIdade(dataHoje, nascimento):
    diaHoje = int(dataHoje[0])
    mesHoje = int(dataHoje[1])
    anoHoje = int(dataHoje[2])

    anoNc = int(nascimento[-4:])
    mesNc = int(nascimento[-7:-5])
    diaNc = int(nascimento[-10:-8])

    if anoHoje < anoNc:
        return "O ano atual é menor que o ano de nascimento"
    
    # Calcula a idade
    idade = anoHoje - anoNc

    if mesHoje < mesNc:
        idade -= 1
    elif mesHoje == mesNc:
        if diaHoje < diaNc:
            idade -= 1        

    return idade          

# Main
nomeEntrada = input("Informe o nome do arquivo de entrada: ")

# Obtém a data
while True:
    hoje = input("Informe a data de hoje (DD MM AAAA): ")

    splitHoje = hoje.strip().split()
    if len(splitHoje) != 3 or len(hoje.strip()) != 10:
        print("Data de hoje inválida. Informe uma data no formato DD MM AAAA.")
    else:
        break

dataHoje = hoje.strip().split()

try:

    with open(nomeEntrada, "r") as arqEntrada:
        lista = arqEntrada.readlines()

    with open("saida_L8Q13", "w") as arqSaida:
        for pessoa in lista:
            linha = pessoa.strip()

            if len(linha) < 11:
                continue

            nome = linha[:-10].rstrip()
            nascimento = linha[-10:] 

            idade = calcularIdade(dataHoje, nascimento)

            arqSaida.write(f"{nome} {idade} anos\n")

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")