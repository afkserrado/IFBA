'''
Lista de exercícios 2
4. Calcular o salario liquido de um professor. Os dados fornecidos serão: a) valor hora aula; b) numero de
aulas dadas; c) percentual de desconto INSS.
'''

valor = float(input("Informe o valor da hora/aula: "))
aulas = int(input("Informe o número de aulas dadas: "))
desc = float(input("Informe o percentual de desconto do INSS: "))

liquido = valor * aulas * (1 - desc/100)

print("\nO salário líquido é: ", liquido, "reais")
print('O salário líquido é {} reais.'.format(liquido))