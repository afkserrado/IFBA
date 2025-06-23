'''
4.Entrar com 2 números e imprimir o maior número.
'''

maior = int(input("Informe um numero a: "))
numb = int(input("Informe um numero b: "))

if numb > maior:
    print(f"O numero {numb} e maior que o numero {maior}.")
elif maior > numb: 
    print(f"O numero {maior} e maior que o numero {numb}.")
else:
    print("Os numeros",maior,"e",numb,"sao iguais")

