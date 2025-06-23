'''
2. Crie um dicionário vazio semana = {} e o complete com uma chave para cada dia da semana,
tendo como seu valor uma lista com as aulas que você tem nesse dia (sábado e domingo
recebem listas vazias, ou você tem aula?).
'''

semana = {}

semana["Segunda"] = ["Arquitetura", "Algoritmos"]
semana["Terça"] = ["Arquitetura"]
semana["Quarta"] = ["Algoritmos", "Laboratorio de programacao"]
semana["Quinta"] = ["Algoritmos"]
semana["Sexta"] = ["Python", "Laboratorio de programacao"]
semana["Sabado"] = []
semana["Domingo"] = []

print("Aulas:", semana)