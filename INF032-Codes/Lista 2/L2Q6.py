'''
Lista de exercícios 2
6. Entrar com valor de um empréstimo, a taxa de juros e a quantidade de meses. Fazer um programa que
calcule o valor da prestação.
'''

emprestimo = float(input("Informe o valor do empréstimo: "))
taxa = float(input("Informe a taxa de juros: "))
meses = int(input("Informe a quantidade de meses: "))

parcela = emprestimo / meses
prestacao = parcela * (1 + (taxa / 100))

print("A prestação é {:.2f} reais.".format(prestacao))