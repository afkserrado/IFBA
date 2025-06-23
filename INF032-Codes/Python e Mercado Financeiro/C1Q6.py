'''
6. Abrir o arquivo bov.txt do exercício anterior no Console e imprimir o resultado
dos elementos existentes nele.
'''

# Abre o arquivo
bov = open("bov.txt", "r")

# Lê os dados
dados = bov.read()

# Imprime
print(dados)

# Fecha o arquivo
bov.close()