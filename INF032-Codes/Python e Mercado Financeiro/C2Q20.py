'''
20. Uma lista está repleta de letras repetidas: 
let = ['a','b','c','a','d','f','a','b','b','d','c'].
Fazer um algoritmo em Python para criar uma nova lista apenas com elementos não repetidos da lista anterior. A resposta deve ser ['a','b','c','d','f'].
'''

# Inicializações
let = ['a','b','c','a','d','f','a','b','b','d','c']
let2 = []

for letra in let:
    achou = 0
    for letra2 in let2:
        if letra == letra2:
            achou = 1
            break
    
    if achou == 0:
        let2.append(letra)

print(let2)




