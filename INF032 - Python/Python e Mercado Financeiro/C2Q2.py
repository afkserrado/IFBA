'''
2. Dados três lados de um triângulo qualquer, fazer um programa para descobrir se o triângulo é equilátero, isósceles ou escaleno. Entrar com os valores com input.
'''
# Cria uma lista vazia
lados = []

# Entrada de dados 
for i in range(0,3):
    lados.append(float(input(f"Informe o {i + 1}º lado do triângulo: ")))

# Verificando as condições
if lados[0] == lados[1] == lados[2]:
    print("O triângulo é equilátero.")
elif lados[0] == lados[1] or lados[1] == lados[2] or lados[0] == lados[2]:
    print("O triângulo é isóceles.")
else:
    print("O triângulo é escaleno.")