'''
3. Criar a lista de números num=[3, 3, 4, 1, 2, 1, 1, 2, 3, 4, 4, 1, 1, 5, 2] e fatiá-la conforme os itens a seguir:
(a) Fatiar do elemento de índice 2 ao de índice 3.
(b) Fatiar do quinto elemento ao nono elemento.
(c) Fatiar do elemento de índice 1 ao último.
(d) Fatiar do primeiro elemento ao último.
(e) Fatiar do primeiro elemento ao último saltando de três em três elementos.
(f) Selecionar o último elemento da lista.
(g) Selecionar os três últimos elementos da lista.
(h) Selecionar os quatro primeiros elementos da lista.
(i) Contar o número de elementos da lista.
(j) Contar quantas vezes aparece o número 1 na lista.
'''

num = [3, 3, 4, 1, 2, 1, 1, 2, 3, 4, 4, 1, 1, 5, 2]
# print(f"num = {}")

# (a)
print(f"a) num = {num[2:4]}")

# (b)
print(f"b) num = {num[4:9]}")

# (c)
print(f"c) num = {num[1:]}")

# (d)
print(f"d) num = {num[:]}")

# (e)
print(f"e) num = {num[0::3]}")

# (f)
print(f"f) num = {num[-1]}")

# (g)
print(f"g) num = {num[-3:]}")

# (h)
print(f"h) num = {num[:4]}")

# (i)
print(f"i) len = {len(num)}")

# (j)
print(f"j) num = {num.count(1)}")