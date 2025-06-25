'''
 6. A tabela a seguir fornece os descontos de uma compra. Fazer um programa em Python que leia o valor de uma compra, determine o desconto a ser aplicado e calcule o valor a ser pago pelo cliente. Imprimir o valor a ser pago com PRINT( ).
 
Valor da compra (R$)    Desconto (%)
Entre 0 e 20            5
Entre 21 e 50           10
Entre 51 e 100          15
Entre 101 e 1.000       20
Maior que 1.000         30
'''

# Entrada de dados
vCompra = float(input("Informe o valor da compra: "))

# Determinando o desconto
if vCompra < 20:
    desconto = 5
elif 21 <= vCompra <= 50:
    desconto = 10
elif 51 <= vCompra <= 100:
    desconto = 15
elif 101 <= vCompra <= 1000:
    desconto = 20
else:
    desconto = 30

# Imprimindo o resultado
print()
vPago = vCompra * (100 - desconto)/100
print(f"O valor da compra é de {vCompra:.2f}. O desconto é de {desconto}% e o valor a ser pago, {vPago:.2f}.")