'''
4. Criar a lista com nomes das bolsas de valores do mundo: Bolsas = ['dow', 'ibov', 'ftse', 'dax', 'nasdaq', 'cac']. Fatiá-la conforme os itens a seguir.
(a) Selecionar as três primeiras.
(b) Incluir a sublista Bs = ['hong kong', 'merval'] na lista anterior.
(c) Descobrir qual o índice da 'nasdaq'.
(d) Remover 'cac' da lista.
(e) Inserir “sp&500” como índice 2 na lista de bolsas, mas sem excluir nenhum elemento já inscrito.
'''

Bolsas = ['dow', 'ibov', 'ftse', 'dax', 'nasdaq', 'cac']

# (a)
print(f"a) {Bolsas[:3]}")

# (b)
Bs = ['hong kong', 'merval']
Bolsas2 = Bolsas.copy()

Bolsas.append(Bs)
print(f"b) {Bolsas[:]}")

Bolsas2.extend(Bs)
print(f"b) {Bolsas2[:]}")

# (c)
print(f"c) {Bolsas.index('nasdaq')}")

# (d)
Bolsas.remove("cac")
print(f"d) {Bolsas[:]}")

# (e)
Bolsas.insert(2, "sp&500")
print(f"e) {Bolsas[:]}")