'''
Lista de exercícios 2
8. Dado um numero de conta corrente com três dígitos, retorne o seu digito verificador, o qual é calculado da
seguinte maneira. Exemplo: numero conta 235, somar o numero da conta com seu inverso : 235+532=767.
Multiplicar cada digito pela sua ordem posicional e somar estes resultados: 7 6 7 (7x1+6x2+7x3) = 40. O
ultimo digito desse resultado é o digito verificador da conta (40-> 0 )
'''

conta = int(input("Informe a conta corrente: "))

# Separando os dígitos da conta
a1 = conta // 100           # Primeiro dígito
a2 = (conta // 10) % 10     # Segundo dígito
a3 = conta % 10             # Terceiro dígito

# Calculando o inverso da conta
inverso = a3 * 100 + a2 * 10 + a1

# Soma da conta com seu inverso
soma = conta + inverso

# Separando os dígitos da soma
a1 = soma // 100           # Primeiro dígito da soma
a2 = (soma // 10) % 10     # Segundo dígito da soma
a3 = soma % 10             # Terceiro dígito da soma

# Calculando o dígito verificador
resultado = a1 + a2 * 2 + a3 * 3
dv = resultado % 10

# Exibindo o dígito verificador
print("Dígito verificador:", dv)


