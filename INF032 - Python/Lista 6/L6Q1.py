'''
1. Entrar como n's números e imprimir o triplo de cada. o programa encerra quando entrar com o numero 999.

OK
'''

numero = 0
while numero != 999:
    numero = int(input("Informe um numero: "))
    if numero == 999:
        print(f"Programa encerrado.")
        break
    print(f"O triplo e: {numero * 3}\n")