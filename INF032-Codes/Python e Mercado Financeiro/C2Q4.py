'''
4. O “suporte” de uma ação é calculado como 30% do intervalo histórico de uma ação (máximo subtraído do mínimo). A “resistência” é calculada como o valor de 60% do intervalo histórico. Veja o exemplo:
    
Baixa histórica: 1.50
Alta histórica: 2.20
Suporte: 1.5 + (2.20 – 1.50) * 0.3
Resistência: 1.5 + (2.20 – 1.5) * 0.6

Fazer um programa em Python em que o usuário forneça com input o valor mais baixo historicamente e o valor mais alto. O programa pede o valor atual da ação também com input e diz com PRINT( ) qual é o suporte, qual é a resistência e se o preço da ação está dentro da faixa de suporte-resistência ou fora dela.
'''
# Entradas de dados
baixa = float(input("Informe a baixa histórica da ação: "))
alta = float(input("Informe a alta histórica da ação: "))
atual = float(input("Informe o valor atual da ação: "))

# Cálculos
suporte = baixa + (alta - baixa) * 0.3
resistencia = baixa + (alta - baixa) * 0.6

# Impressões
print("")
print(f"O suporte é {suporte:.2f} e a resistência, {resistencia:.2f}.")

if suporte <= atual <= resistencia:
    print("O valor atual está dentro do intervalo suporte-resistência.")
else:
    print("O valor atual está fora do intervalo suporte-resistência.")