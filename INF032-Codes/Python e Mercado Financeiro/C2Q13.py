'''
13. Fazer um algoritmo em Python para percorrer uma lista e imprimir com PRINT( ) apenas ações do mesmo setor. Lembrar e usar o comando continue para essa tarefa. Por exemplo, na lista a seguir, excluir com o uso do while a sigla da Petrobras (petr4) do setor de petróleo, visto que a lista possui bancos.
'''

lista = ['bbdc4', 'itub4', 'petr4', 'petr4', 'bbas3', 'petr4', 'sanb4',  'petr4', 'bpac3', 'petr4']
tam = len(lista) # 10

i = 0
while i < tam:
    if lista[i] == "petr4":
        i += 1
        continue

    print(f"Ação de Banco :  {lista[i]}")

    # Incremento
    i += 1