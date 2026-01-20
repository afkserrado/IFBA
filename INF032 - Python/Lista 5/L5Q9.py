'''
9. Um comerciante comprou um produto e quer vende-lo com um lucro de 45% se o valor da compra for menor que 20,00, caso contrário o lucro será de 30%. entrar com o valor do produto e imprimir o valor da venda.
'''

pcompra = float(input("Informe o preco de compra: "))

if pcompra < 20: pvenda = pcompra * 1.45 # 45% de lucro
else: pvenda = pcompra * 1.30 # 30% de lucro

print(f"O preco de venda e R${pvenda:.2f}.")