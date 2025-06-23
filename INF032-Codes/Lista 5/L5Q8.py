'''
8. Entrar com o nome, nota 1 e nota 2 de um aluno, imprimir nome, Nota1, nota2, média e a mensagem aprovado, reprovado ou em prova final. (a média é 7 para aprovado, 3 para reprovado, e as demais para prova final.
'''

nome = input("Informe o nome do aluno: ")
nota1 = float(input("Informe a nota 1 "))
nota2 = float(input("Informe a nota 2: "))

media = (nota1 + nota2)/2

if media >= 7:
    status = "APROVADO"
elif media <= 3:
    status = "REPROVADO"
else:
    status = "PROVA FINAL"

print(f"Nome: {nome} | Nota 1: {nota1:.2f} | Nota 2: {nota2:.2f} | Media: {media:.2f} | Resultado: {status}.")