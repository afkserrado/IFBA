'''
2.Entrar com números enquanto forem positivos, e imprimir quantos números foram digitados.

OK
'''

numero = 1
cont = 0

while numero > 0:
    numero = int(input("Informe um numero positivo: "))
    
    if numero > 0:
        cont = cont + 1

print(f"Foram digitados {cont} numeros positivos e 1 negativo.")