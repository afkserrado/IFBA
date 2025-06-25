'''
 7. Uma loja de departamentos de roupas de cama e banho percebeu que, se separar seu servidor de vendas em pedidos pares e ímpares, o atendimento fica mais ágil. Fazer um algoritmo em Python em que se deve ler o número que representa o total de vendas n de uma sequência de números naturais e, posteriormente, ler a própria sequência com input. Armazenados os números da sequência, o programa deve mostrar com PRINT( ) o valor da soma dos números pares (SP) e o valor da soma dos números ímpares (SI).
'''

# Entrada de dados
n = int(input("Informe o total de vendas: "))

# Inicializa as somas
sp = 0
si = 0

for i in range(0, n):
    num = int(input("Informe um número: "))
    
    # Par
    if num % 2 == 0: 
        sp += num
        
    # Ímpar
    else: 
        si += num 
        
# Impressão
print(f"Soma par = {sp} | Soma ímpar = {si}.")