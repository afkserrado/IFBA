'''
4. Faça um programa que receba do usuário um arquivo texto e um caractere. Mostre na tela quantas vezes aquele caractere ocorre dentro do arquivo.
'''
# Conta quantas vezes uma letra aparece
def countletter(letra, conteudo):
    
    letra = letra.lower()
    conteudo = conteudo.lower()

    count = 0
    for char in conteudo:
        if letra == char:
            count += 1

    return count          

caminho = input("Informe o caminho do arquivo: ")
caminho = caminho.replace("\\", "/")

try: 
    arquivo = open(caminho, "r")
    conteudo = arquivo.read()

    letra = input("Informe uma letra: ")
    
    if len(letra) != 1:
        print("Por favor, digite apenas um único caractere.")
    else:
        qtd = countletter(letra, conteudo)
        print(f"A letra {letra} aparece {qtd} vezes no arquivo.")

    arquivo.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")
