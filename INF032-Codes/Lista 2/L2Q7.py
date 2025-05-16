'''
Lista de exercícios 2
7. Criar um programa que leia a quantidade de fotos de uma locadora de vídeo possui e o valor que ela cobra
por cada aluguel, mostrando as informações pedidas a seguir: a) sabendo que um terço das fitas são
alugadas por mês, exiba o faturamento anual da locadora; b)Quando o cliente atrasa a entrega, é cobrada
uma multa de 10% sobre o valor do aluguel. Sabendo que um decimo das fitas alugadas no mês são
devolvidas com atraso, calcule o valor ganho com multas por mês; c) sabendo ainda que 2% de fitas se
estragam ao longo do ano, e um decimo do total é comprado para reposição, exiba a quantidade de fitas
que a locadora terá no final do ano.
'''

qtd = int(input("Informe a quantidade de fitas: "))
aluguel = float(input("Informe o valor do aluguel: "))


fitasMes = int(qtd/3) # Fitas por mês
fatAnual = fitasMes * 12 * aluguel # Faturamento anual
multas = fitasMes/10 * aluguel * 0.10 # Multas mensais
estragadas = qtd * 0.02 # Fitas estragadas por ano
resto = qtd - estragadas # Sobra
reposicao = resto / 10 # Quantidade reposta
total = int(resto + reposicao)

print("Quantidade = {}; Fitas alugadas/mês = {}; Faturamento anual = R${:.2f}; Multas = R${:.2f}; Total de fitas: {}".
      format(qtd, fitasMes, fatAnual, multas, total))