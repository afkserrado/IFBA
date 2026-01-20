'''
15.A prefeitura do Rio de Janeiro abriu uma linha de crédito para seus funcionários. O valor máximo da prestação não poderá ultrapassar 30% do salário bruto. Fazer um programa que permita entrar com o salário bruto e o valor da prestação e informar se o empréstimo pode ou não ser concedido.
'''

salario = float(input("Informe o salario bruto do funcionario: "))
prestacao = float(input("Informe a prestacao: "))

limite = salario * 0.30

if prestacao > limite:
    print(f"O emprestimo nao pode ser concedido.")
else: 
    print(f"O emprestimo pode ser concedido.")
