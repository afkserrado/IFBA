'''
12. Faça um programa que permita que o usuario entre com diversos nomes e telefone para cadastro, e crie um arquivo com essas informacoes, uma por linha. O usuario finaliza a entrada com '0' para o telefone.
'''

try: 
    
    while True:
        nome = input("Informe um nome: ")
        tel = input("Informe o número de telefone (XXXXX-XXXX) ou digite '0' para sair: ")

        if tel == '0':
            break

        with open("cadastro.txt", "a+") as arq:
            arq.write(f"Nome: {nome} | Telefone: {tel}\n")

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")