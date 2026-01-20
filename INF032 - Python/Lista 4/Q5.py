'''
5. Crie um dicionário que é uma agenda e coloque nele os seguintes dados: chave (cpf), nome,
idade, telefone. O programa deve ler um 5 dados completos, e imprimir todos os itens do
dicionário no formato chave: nome-idade-fone.
'''

agenda = {}

agenda["111.222.333-44"] = dict({"nome": "Anderson", "idade": 29, "telefone": "(71) 91111-2222"})
agenda["222.111.444-33"] = dict({"nome": "Rafaela", "idade": 32, "telefone": "(71) 92222-1111"})
agenda["333.444.222-11"] = dict({"nome": "Luan", "idade": 22, "telefone": "(71) 93333-4444"})
agenda["555.666.777-88"] = dict({"nome": "Henrique", "idade": 46, "telefone": "(71) 97766-8800"})
agenda["777.888.999-00"] = dict({"nome": "Amanda", "idade": 19, "telefone": "(71) 94589-7894"})

print("\n")
print("Agenda:", agenda)
print("\n")
print("111.222.333-44:", agenda["111.222.333-44"]["nome"],"-",agenda["111.222.333-44"]["idade"],"-",agenda["111.222.333-44"]["telefone"])
print("222.111.444-33:", agenda["222.111.444-33"]["nome"],"-",agenda["222.111.444-33"]["idade"],"-",agenda["222.111.444-33"]["telefone"])
print("333.444.222-11:", agenda["333.444.222-11"]["nome"],"-",agenda["333.444.222-11"]["idade"],"-",agenda["333.444.222-11"]["telefone"])
print("555.666.777-88:", agenda["555.666.777-88"]["nome"],"-",agenda["555.666.777-88"]["idade"],"-",agenda["555.666.777-88"]["telefone"])
print("777.888.999-00:", agenda["777.888.999-00"]["nome"],"-",agenda["777.888.999-00"]["idade"],"-",agenda["777.888.999-00"]["telefone"])
print("\n")