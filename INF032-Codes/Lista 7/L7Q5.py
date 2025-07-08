'''
5)Faça um programa com uma função chamada somaImposto. A função possui dois parâmetros formais: taxaImposto, que é a quantia de imposto sobre vendas expressa em porcentagem, e custo, que é o custo de um item antes do imposto. A função “altera” o valor de custo para incluir o imposto sobre vendas.
'''

# Calcula o custo com o imposto embutido
def somaImposto(taxaImposto, custo):
    return custo * (1 + taxaImposto / 100)

# Main
taxaImposto = float(input("Informe a taxa de imposto em porcentagem: "))

if taxaImposto < 0:
    print("O imposto não pode ser negativo.")
else:
    custo = float(input("Informe o valor do produto/serviço: "))

    custo = somaImposto(taxaImposto, custo)
    print(f"O custo final será {custo:.2f}.")

