'''
7. Observar a seguinte lista de dados, lista= [2, 2, 3, 3, 3, -1, -1, -2, 0, 0, 0, 2, 4, 5, 1, 2, 2, 0, 0, 0,2 ,1, 5, 5, 7, 6, 5, 0, 0]. Programar o Console para encontrar as seguintes medidas estatísticas:
    
(a) Soma de todos os elementos.
(b) Máximo elemento da lista.
(c) Mínimo elemento da lista.
(d) Média dos elementos da lista.
(e) Mediana dos elementos da lista.
(f) Moda dos elementos da lista.
(g) Desvio-padrão amostral.
(h) Desvio-padrão populacional.
(i) Contar o número de vezes que aparece o número 0.
(j) Contar o número de vezes que aparece o número 5.
(k) Ordenar a lista em ordem crescente.
(l) Ordenar a lista em ordem decrescente.
'''

from numpy import array
import statistics as st

lista = [2, 2, 3, 3, 3, -1, -1, -2, 0, 0, 0, 2, 4, 5, 1, 2, 2, 0, 0, 0, 2 , 1, 5, 5, 7, 6, 5, 0, 0]
vetor = array(lista)

# (a)
print(f"a) sum = {sum(vetor)}")

# (b)
print(f"b) máx = {max(vetor)}")

# (c)
print(f"c) mín = {min(vetor)}")

# (d)
print(f"d) média = {st.mean(vetor)}")

# (e)
print(f"e) mediana = {st.median(vetor)}")

# (f)
print(f"f) moda = {st.mode(vetor)}")

# (g)
print(f"g) desvio-padrão amostral = {st.stdev(lista)}")

# (h)
print(f"h) desvio-padrão populacional = {st.pstdev(lista)}")

# (i)
print(f"i) cont(0) = {lista.count(0)}")

# (j)
print(f"j) cont(5) = {lista.count(5)}")

# (k)
lista.sort()
lista2 = sorted(lista)
print(f"k) lista ordenada cres = {lista}")
print(f"k) lista2 = {lista2}")

# (l)
lista.reverse()
lista2 = sorted(lista, reverse = True)
print(f"l) lista ordenada decres = {lista}")
print(f"l) lista2 = {lista2}")