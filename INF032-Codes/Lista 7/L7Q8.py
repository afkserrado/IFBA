'''
8)Crie uma função que receba duas palavras e retorne True caso a primeira palavra seja um prefixo da segunda. Exemplo: ’uf’ é prefixo de ’ufabc’. ’ufabc’ não é prefixo de ’uf’.

Obs: não é case sensitive.
'''

def ehPrefixo(p1, p2):

    # Converte para minúsculas
    p1 = p1.lower()
    p2 = p2.lower()

    # Verifica se p1 é maior que p2
    if len(p1) > len(p2):
        return False
    
    i = 0
    for letra in p1:
        if letra != p2[i]: 
            return False # Não é prefixo
        else:
            i += 1

    return True # É prefixo

# Main
p1 = input("Informe a primeira palavra: ")
p2 = input("Informe a segunda palavra: ")

prefixo = ehPrefixo(p1, p2)
print(prefixo)