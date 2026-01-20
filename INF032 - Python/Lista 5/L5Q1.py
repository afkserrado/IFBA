'''
1. Que entre com um número e informe se o mesmo está compreendido entre 20 e 90.
'''

numero = int(input("Informe um numero: "))

if numero >= 20 and numero <= 90:
    print("O numero", numero, "esta compreendido entre 20 e 90.")
else:
    print("O numero", numero, "nao esta compreendido entre 20 e 90.")