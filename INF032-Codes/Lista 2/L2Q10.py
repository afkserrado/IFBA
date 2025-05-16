'''
Lista de exercícios 2
10. Dada a frase “Python é muito legal". use fatiamento para dar nome às variáveis contendo cada palavra.
Qual o tamanho dessa frase? E qual o tamanho de cada palavra?
'''

frase = "Python é muito legal"
tam = len(frase) # Tamanho da frase

palavras = frase.split() # Separando a frase

p1, p2, p3, p4 = palavras # Guardando as palavras

# Calculando o tamanho das palavras
tam1 = len(p1)
tam2 = len(p2)
tam3 = len(p3)
tam4 = len(p4)

print("A frase fatiada é: {} | {} | {} | {}; O tamanho da frase é: {}; O tamanho de cada palavra é: {}, {}, {} e {}".
      format(p1, p2, p3, p4, tam, tam1, tam2, tam3, tam4))