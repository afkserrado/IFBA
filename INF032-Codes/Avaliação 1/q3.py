'''
3) Faça um programa que calcule o menor número possível de notas (cédulas) que um valor, inserido pelo usuário, pode ser decomposto. As notas consideradas são de 100, 50, 20, 10, 5, 2 e 1.
'''

valor = int(input("Informe um valor inteiro: "))
cedulas = {100: 0, 50: 0, 20: 0, 10: 0, 5: 0, 2: 0, 1: 0}

if valor == 0:
    print("O valor não pode ser 0.")

else:
    for c in cedulas:
        cedulas[c] = valor // c # Quantidade de cada cédula
        valor %= c # Atualiza o valor

total = sum(cedulas.values())
print(f"O total de cédulas é {total:.0f}.")
print(f"Cedulas: {cedulas}")