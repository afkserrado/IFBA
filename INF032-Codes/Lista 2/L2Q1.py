'''
Listas 002 de Exercícios
1. Entrar com dois números inteiros e imprimir a seguinte saída: a)dividendo; b) divisor; c) quociente; d) resto.
'''

'Leitura de dados'
dividendo = int(input("Informe o 1º valor: "))
divisor = int(input("Informe o 2º valor: "))

quociente = dividendo/divisor
resto = dividendo%divisor

print("Dividendo = ", dividendo, ", divisor = ", divisor, ", quociente = ", quociente, "e resto = ", resto)
print('Dividendo = {}, divisor = {}, quociente = {} e resto = {}.'.format(dividendo, divisor, quociente, resto))


