'''
7. Desenvolver um programa que leia o conteúdo de um arquivo e cria um arquivo com o mesmo conteúdo, mas com todas as letras minúsculas convertidas para maiúsculas. Os nomes dos arquivos serão fornecidos, via teclado, pelo usuário.
'''

caminho = input("Informe o caminho, com nome, do arquivo entrada: ")
caminho = caminho.replace("\\", "/")

try: 
    arquivo = open(caminho, "r", encoding="utf-8")
    conteudo = arquivo.read()

    caminho2 = input("Informe o caminho, com nome, do arquivo de saída: ")
    caminho2 = caminho2.replace("\\", "/")

    conteudo2 = ""
    for char in conteudo:
        if char.islower():
            conteudo2 += char.upper()
        else: 
            conteudo2 += char            

    arquivo2 = open(caminho2, "w", encoding="utf-8")
    arquivo2.write(conteudo2)

    arquivo.close()
    arquivo2.close()

# Erro ao ler arquivo ou arquivo não encontrado
except (OSError):
    print("Erro ao ler arquivo.")