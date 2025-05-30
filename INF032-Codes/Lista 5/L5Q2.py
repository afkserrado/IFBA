'''
2.Entrar como nome, idade e sexo de uma pessoa. se a pessoa for do sexo feminino e tiver menos de 25 anos, imprimir a mensagem (ACEITA), caso contrário, imprimir NAO ACEITA.
'''

nome = input("Informe um nome: ")
idade = int(input("Informe uma idade: "))
sexo = input("Informe o sexo: ")

if (sexo == "feminino" or sexo == "FEMININO" or sexo == "F" or sexo == "f") and idade < 25:
    print("\nACEITA")
else:
    print("\nNAO ACEITA")