'''
5.Entrar com nomes enquanto forem diferentes de FIM e imprimir o primeiro caracter de cada nome;
'''

nome = "0"
while nome != "FIM":
    nome = input("Informe um nome (digite FIM para encerrar): ")

    if nome == "":
        print("Voce nao digitou um nome.\n")
        continue

    if nome != "FIM":
        print(f"Nome: {nome} | Primeira letra: {nome[0]}\n")

print("\nPrograma encerrado.")