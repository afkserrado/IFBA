'''
4)Um número é dito perfeito quando ele é igual a soma de seus fatores. Por exemplo, os fatores de 6 são 1, 2 e 3 (ou seja, podemos dividir 6 por 1, por 2 e por 3) e 6=1+2+3, logo 6 é um número perfeito. Escreva uma função que recebe um inteiro e dizer se é perfeito ou não. Em outra função, peça um inteiro n e mostre todos os números perfeitos até n.
'''

# Recebe um número
def pegaNum():
    num = int(input("Informe um número: "))
    if num < 0:
        print("O número não pode ser negativo.")
        return None
    else:
        return num

# Verifica se um número é perfeito
def ehPerfeito(num, opcao):
    
    perfeito = True
    if num < 6: # O menor perfeito é 6
        perfeito = False
    else: # Testa se o número é perfeito
        sFatores = 0 # Soma dos fatores
        for i in range(1, (num // 2) + 1):
            if num % i == 0:
                sFatores += i
        
        if sFatores != num:
            perfeito = False

    if opcao == 1:
        if perfeito == True:
            print(f"O número {num} é perfeito.")
        else:
            print(f"O número {num} não é perfeito.")

    elif opcao == 2:
        return perfeito

# Exibe todos os números perfeitos até n
def saoPerfeitos(num, opcao):
    print("Números perfeitos:", end = " ")
    for i in range(1, num + 1):
        if ehPerfeito(i, opcao) == True:
            print(i, end = " ")
    print()

# Main
print("## Números Perfeitos ##")
print("1 - Testa se um número é perfeito")
print("2 - Mostra todos os perfeitos até n")
opcao = int(input("Informe uma opção: "))

if opcao == 1:
    ehPerfeito(pegaNum(), opcao)
elif opcao == 2:
    saoPerfeitos(pegaNum(), opcao)
else:
    print("Opção inválida.")