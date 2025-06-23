'''
6.Entrar com vários números ate entrar com o numero 999. para cada numero imprimir seus divisores.

OK
'''

entrada = ""
while entrada != "999":
    entrada = input("Informe um numero: ")
    
    if entrada == "":
        print("Voce nao digitou um numero.\n")
        continue
    elif entrada == "999":
        break
    
    numero = int(entrada)

    if numero == 0:
        print("O número 0 não possui divisores.\n")
        continue

    print(f"Divisores de {numero}:")
    for i in range(1, numero // 2 + 1):
        if numero % i == 0:
            print(i)

    ## O número é divísivel por ele mesmo
    if numero != 0:
        print(f"{numero}\n")
    else:
        print("O numero 0 nao possui divisores.\n")
            
print(f"Programa encerrado.")