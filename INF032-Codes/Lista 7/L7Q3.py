'''
3) Crie uma função que recebe um inteiro positivo e teste para saber se ele é primo ou não. Faça um script que recebe um inteiro n e mostra todos os primos, de 1 até n.
'''

# Recebe um número
def pegaNum():
    num = int(input("Informe um número: "))
    if num < 0:
        print("O número não pode ser negativo.")
        return None
    else:
        return num

# Verifica se um número é primo
def ehPrimo(num, opcao):
    
    if num < 2: # O menor primo é 2
        primo = False
    else: # Testa se o número é primo
        primo = True
        for i in range(2, (num // 2) + 1):
            if num % i == 0:
                primo = False
                break

    if opcao == 1:
        if primo == True:
            print(f"O número {num} é primo.")
        else:
            print(f"O número {num} não é primo.")

    elif opcao == 2:
        return primo

# Exibe todos os números primos até n
def saoPrimos(num, opcao):
    print("Números primos:", end = " ")
    for i in range(1, num + 1):
        if ehPrimo(i, opcao) == True:
            print(i, end = " ")
    print()

# Main
print("## Números primos ##")
print("1 - Testa se um número é primo")
print("2 - Mostra todos os primos até n")
opcao = int(input("Informe uma opção: "))

if opcao == 1:
    ehPrimo(pegaNum(), opcao)
elif opcao == 2:
    saoPrimos(pegaNum(), opcao)
else:
    print("Opção inválida.")