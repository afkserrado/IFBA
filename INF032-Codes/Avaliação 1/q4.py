'''
4) Faça um programa que recebe uma string do usuário e informa se ela é um palíndromo. Um palíndromo é uma frase que, excluindo os espaços em branco, pode ser lida indiferentemente da esquerda para a direita e da direita para a esquerda. Alguns palíndromos conhecidos são: ovo, radar, a grama é amarga, a base to teto desaba.
'''

string = input("Informe uma palavra ou frase: ")

# Removendo espaços em branco
# Método 1
'''str_nova = string.replace(" ", "")
#print(str_nova)'''

# Método 2
'''str_nova = ''.join(string.split())
print(str_nova)'''

# Método 3
str_nova = ""
for palavra in string:
    if palavra != " ":
        str_nova += palavra.lower()

# Invertendo a string
# Método 1
'''str_invertida = ''.join(reversed(str_nova))
print(str_invertida)'''

# Método 2
str_invertida = str_nova[::-1]
#print(str_invertida)

# Verificando se é palíndromo
if str_nova == str_invertida:
    print("A string é um palíndromo.")
else:
    print("A string não é um palíndromo.")