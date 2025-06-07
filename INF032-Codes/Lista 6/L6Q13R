'''
13) O Hipermercado Tabajara está com uma promoção de carnes que é imperdível. Confira:
Até 5 Kg Acima de 5 Kg
File Duplo R$ 4,90 por Kg R$ 5,80 por Kg
Alcatra R$ 5,90 por Kg R$ 6,80 por Kg
Picanha R$ 6,90 por Kg R$ 7,80 por Kg

Para atender a todos os clientes, cada cliente poderá levar apenas um dos tipos de carne da promoção, porém não há limites para a quantidade de carne por cliente. Se compra for feita no cartão Tabajara o cliente receberá ainda um desconto de 5% sobre o total da compra.

Escreva um programa que peça o tipo e a quantidade de carne comprada pelo usuário e gere um cupom fiscal, contendo as informações da compra: tipo e quantidade de carne, preço total, tipo de pagamento, valor do desconto e valor a pagar.
'''

# Listas com os preços por quilo (índice 0: Filé Duplo, 1: Alcatra, 2: Picanha)
lista1 = [4.90, 5.90, 6.90]  # até 5 kg
lista2 = [5.80, 6.80, 7.80]  # acima de 5 kg
nomes_carnes = ["Filé Duplo", "Alcatra", "Picanha"]

while True:
    print("\nTipos de carne disponíveis:")
    print("1 - Filé Duplo")
    print("2 - Alcatra")
    print("3 - Picanha")
    tipo = input("Digite o número do tipo de carne desejado (ou -1 para sair): ")

    if tipo == "-1":
        break

    tipo = int(tipo)
    if tipo not in [1, 2, 3]:
        print("Tipo inválido. Tente novamente.\n")
        continue

    tipo -= 1  # Ajusta para índice da lista (base 0)

    quantidade = float(input("Digite a quantidade (em kg): "))

    print("\nFormas de pagamento:")
    print("1 - Cartão Tabajara (5% de desconto)")
    print("2 - Outro")
    pagamento = int(input("Digite o número da forma de pagamento: "))

    if quantidade <= 5:
        pKg = lista1[tipo]
    else:
        pKg = lista2[tipo]

    total = quantidade * pKg

    if pagamento == 1:
        desconto = 0.05
        tipo_pagamento = "Cartão Tabajara"
    else:
        desconto = 0
        tipo_pagamento = "Outro"

    valor_desconto = total * desconto
    valor_final = total - valor_desconto

    # Cupom Fiscal
    print("\n=== CUPOM FISCAL ===")
    print(f"Tipo de carne: {nomes_carnes[tipo]}")
    print(f"Quantidade: {quantidade:.2f} kg")
    print(f"Preço total: R$ {total:.2f}")
    print(f"Forma de pagamento: {tipo_pagamento}")
    print(f"Valor do desconto: R$ {valor_desconto:.2f}")
    print(f"Valor a pagar: R$ {valor_final:.2f}\n")

print("Programa encerrado.")