# Preços por litro
cA = 1.90  # preço do litro do álcool
cG = 2.50  # preço do litro da gasolina

# Loop principal
while True:
    tipo = input("Digite o tipo de combustível (A para Álcool, G para Gasolina ou -1 para sair): ").upper()
    
    if tipo == "-1":
        break

    litros = float(input("Digite a quantidade de litros vendidos: "))

    if tipo == 'A':
        if litros <= 20:
            dA = 0.03
        else:
            dA = 0.05
        preco = cA * (1 - dA) * litros
    elif tipo == 'G':
        if litros <= 20:
            dG = 0.04
        else:
            dG = 0.06
        preco = cG * (1 - dG) * litros
    else:
        print("Tipo de combustível inválido.\n")
        continue  # pula o restante do loop e volta ao início

    print(f"Valor a ser pago pelo cliente: R$ {preco:.2f}\n")

print("Programa encerrado.")