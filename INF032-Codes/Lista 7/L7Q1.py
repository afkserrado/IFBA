'''
1) Escreva um programa que pergunta ao usuário se ele deseja converter uma temperatura de grau Celsius para Farenheit ou vice-versa. Para cada opção, crie uma função. Crie uma terceira, que é um menu para o usuário escolher a opção desejada, onde esse menu chama a função de conversão correta.
'''

def menu():
    print("## Conversor de temperatura ##")
    print("1 - Celsius para Fahrenheit")
    print("2 - Fahrenheit para Celsius")
    opcao = int(input("Informe a opção desejada: "))
    print()

    if opcao == 1:
        ctof()
    elif opcao == 2:
        ftoc()
    else:
        print("Opcão inválida.")

def ctof():
    tc = float(input("Informe a temperatura em graus Celsius: "))
    tf = (tc * 9/5) + 32
    print(f"A temperatura em Fahrenheit é: {tf:.2f}.")

def ftoc():
    tf = float(input("Informe a temperatura em Fahrenheit: "))
    tc = (tf - 32) * 5/9
    print(f"A temperatura em graus Celsius é: {tc:.2f}.")

menu()
