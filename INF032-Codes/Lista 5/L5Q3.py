'''
3.Entrar com a sigla do estado de uma pessoa, e imprimir se é carioca, Paulista, mineira ou outros estados.
'''

estado = input("Informe a sigla do seu estado: ")
estado = estado.lower()

if estado == 'sp':
    print("A pessoa e paulista.")
elif estado == 'rj':
    print("A pessoa e carioca.")
elif estado == 'mg':
    print("A pessoa e mineira")
else: 
    print("Outros estados.")
