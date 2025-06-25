'''
3. Um website de compras deseja deixar um link para os franqueados entrarem com o valor de compra de um produto no input e o valor de venda no input e descobrir se o lucro foi menor que 10%, entre 10% e 20%, ou superior a 20%. Para tanto, deve-se fazer um programa em Python que imprima a mensagem com PRINT( ) dizendo em qual faixa a mercadoria do comerciante se localiza.
'''

pCompra = float(input("Informe o valor de compra: "))
pVenda = float(input("Informe o valor de venda: "))

lucro = ((pVenda - pCompra) / pCompra) * 100

if lucro < 10:
    print("O lucro é menor que 10%.")
elif 10 <= lucro <= 20:
    print("O lucro está entre 10% e 20%.")
else:
    print("O lucro é maior que 20%.")