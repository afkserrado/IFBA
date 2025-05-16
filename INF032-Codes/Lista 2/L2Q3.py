'''
Lista de exercícios 2
3. Entrar com um nome e imprimir: a) todo o nome; b) primeiro caracter; c) ultimo carcater; d) do primeiro
ate o terceiro caracter; e)quarto caracter; f) os dois últimos.
'''

nome = input("Digite um nome: ")
tam = len(nome)


print("O nome digitado foi: ", nome)
print("O primeiro caractere é: ", nome[0])
print("O último digito é: ", nome[tam - 1])
print("Do 1º ao 3º caractere: ", nome[0:3]) # Do 0 ao antes do 3
print("Quarto caractere: ", nome[3])
print("Os dois últimos caracteres são: ", nome[-2], "e", nome[-1])

