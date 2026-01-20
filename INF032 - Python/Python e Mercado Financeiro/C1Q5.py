'''
5. Abrir um arquivo chamado bov.txt e salvar os dados das siglas das ações e seus valores na seguinte ordem: ‘petr4’, ‘vale3’, ‘ggbr4’, 28.4, 31.3, 15.76.
'''

acoes = ['petr4', 'vale3', 'ggbr4']
valores = [28.4, 31.3, 15.76]

# Cria o arquivo em modo de escrita
bov = open("bov.txt", "w") 

# Escreve no arquivo
bov.write(f"{acoes[0]} = {valores[0]}\n{acoes[1]} = {valores[1]}\n{acoes[2]} = {valores[2]}\n") 

# Salva e fecha o arquivo
bov.close()