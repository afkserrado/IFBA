'''
10. Sabendo-se que somente os municípios que possuem mais de 20 mil eleitores aptos tem segundo turno nas eleições para prefeito caso o primeiro colocado não tenha mais do que 50% DOS VOTOS. FAZER UM PROGRAMA QUE LEIA O NOME do município, a quantidade de eleitores aptos, o número de votos do candidato mais votado e informar se ele terá ou não segundo turno.
'''

municipio = input("Informe o nome do municipio: ")
eleitores = int(input("Informe a quantidade de eleitores: "))
votos = int(input("Informe o numero de votos do candidato mais votado: "))

percentual = (float(votos/eleitores)*100)
#print(f"Percentual: {percentual:.2f}")

# Não tem segundo turno
status = "nao havera segundo turno"

if percentual < 50 and eleitores > 20000:
    status = "havera segundo turno"

print(f"No municipio de {municipio} {status}.")