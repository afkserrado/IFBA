#14.5
limpeza = ['Desinfetante', 'Esponja', 'Bombril', 'Detergente', 'Agua sanitaria']
compras = ['Feijao', 'Arroz', 'Banana', 'Iogurte', 'Leite', limpeza, 'Sorvete']
print("Compras: ", compras)
del compras[5]
print("Compras sem produtos de limpeza: ", compras)
del compras[5]
print("Compras sem produtos de limpeza e sem sorvete: ", compras)