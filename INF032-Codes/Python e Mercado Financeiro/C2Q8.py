'''
 8. O financiamento de um automóvel foi contratado respeitando a série matemática a seguir para 70 meses. Criar um algoritmo em Python para calcular a soma seguinte até o termo n que o usuário desejar, seguindo a lógica a seguir.

S = 70/7 + 69/14 + 68/21 + 67/28 + ... 
'''

# Entrada de dados
meses = int(input("Informe o número de meses: "))

# Inicializações
j = 1
s = 0

for i in range(meses, 0, -1):
    s += i / (7 * j)
    j += 1
    
print(f"A soma é = {s:.2f}.")