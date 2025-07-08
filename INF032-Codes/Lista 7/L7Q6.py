'''
6) Escreva uma funcão em Python que recebe uma lista e retorna uma outra lista contendo apenas os elementos que aparecem duas ou mais vezes na lista de entrada.
Exemplo:

reps([1,4,2,3,4,2,3,4])

reps[2,3,4]
'''

def repetidos(lista):
    unicos = set(lista) # Cria uma lista com números únicos
    lista2 = [] # Inicializa a nova lista

    for i in unicos: # Percorre a lista de números únicos
        cont = 0
        for j in lista: # Percorre a lista informada pelo usuário
            if i == j:
                cont += 1
                if cont >= 2:
                    lista2.append(i) # Adiciona o número na lista 2
                    break # Parada antecipada
    
    return lista2

# Main
entrada = input("Informe uma lista de números separados por espaço: ")
listaEntrada = entrada.split() # Separa a string e guarda na lista

# Converte os elementos da lista de string para int
lista = []
for elemento in listaEntrada:
    lista.append(int(elemento))

lista2 = repetidos(lista)
print(lista2)

