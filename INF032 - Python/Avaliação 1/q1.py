'''
1) Sabendo-se que a UAL calcula a divisão através de subtrações sucessivas, criar um programa que calcule e imprima o resto da divisão de números inteiros lidos. Suponha que os números lidos sejam positivos e que o dividendo seja maior que o divisor.
'''

a = int(input("Informe o dividendo: "))
b = int(input("Informe o divisor: "))

# Verifica se o divisor é 0
if b == 0:
    print("O divisor não pode ser 0.")

# Verifica se o dividendo e o divisor são números positivos
elif a < 0 or b < 0:
    print("Os números não podem ser negativos.")

# Verifica se o dividendo é maior que o divisor
elif a <= b:
    print("O dividendo deve ser maior que o divisor.")

else:
    # Subtrações sucessivas até obter o resto
    while a >= b:
        a = a - b

    print(f"Resto = {a}")