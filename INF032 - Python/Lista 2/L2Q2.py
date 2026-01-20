'''
Lista de exercícios 2
2. Entrar com quatro números e imprimir a media ponderada, sabendo-se que os pesos são respectivamente
1,2,3,4.
'''

a = int(input("Informe o 1º valor: "))
b = int(input("Informe o 2º valor: "))
c = int(input("Informe o 3º valor: "))
d = int(input("Informe o 4º valor: "))

media = (a*1 + b*2 + c*3 + d*4) / (1+2+3+4)

print("A média ponderada é: ", media)