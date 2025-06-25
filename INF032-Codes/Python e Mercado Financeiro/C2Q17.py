'''
17. Na lista do exercício anterior: Palavras = ['comprar','vender'],['manter','alertar', 'indicar'],['tendencia','crash','lucro']], fazer um algoritmo em Python para imprimir no Console os elementos das listas que são elementos da lista original Palavras. Ou seja, devem aparecer na impressão os elementos todos sem os colchetes que representam listas. Nesse caso da lista Palavras, a impressão deve ser:

comprar
vender
manter
alertar
indicar
tendencia
crash
lucro
'''

palavras = [['comprar','vender'],['manter','alertar','indicar'],['tendencia','crash','lucro']]

for lista in palavras:
    for elemento in lista:
        print(elemento)