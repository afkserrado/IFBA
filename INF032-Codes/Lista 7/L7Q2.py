'''
2) A série de Fibonacci é uma sequência de números, cujos dois primeiros são 0 e 1. O termo seguinte da sequência é obtido somando os dois anteriores. Faça uma script em Python que solicite um inteiro positivo ao usuário, n. Então uma função exibe todos os termos da sequência até o n-ésimo termo. Use recursividade.
'''

def fib(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fib(n-1) + fib(n-2)

n = int(input("Informe um número inteiro positivo: "))

if n < 0: 
    print("O número não pode ser negativo.")
else:
    print("A série de Fibonacci é:", end = " ")
    for i in range(n):
        print(fib(i), end = " ")
    print()

