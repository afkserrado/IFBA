'''
13. Entrar com um número e escrever uma das mensagens: “é múltiplo de 3", ou "não é múltiplo de 3."
'''

num = int(input("Informe um numero: "))

if num % 3 == 0:
    print(f"O numero {num} e multiplo de 3.")
else:
    print(f"O numero {num} nao e multiplo de 3.")