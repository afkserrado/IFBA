'''
2. Assumir como constante no comando de linha do Python x = 3 e y = 6 e imprimir usando PRINT( ) o resultado das equações seguintes:
(a) w = e^x − ln(y)
(b) z = x*y^2 + y*cos(x)
(c) s = (x/y + ln(x+y) + tan(x)))^(1/2)
'''

from math import exp, log, sqrt, cos, tan

x = 3
y = 6

# (a)
print(f"w = {exp(x) - log(y)}")

# (b)
print(f"z = {x * y ** 2 + y * cos(x)}")

# (c)
print(f"s = {sqrt(x / y + log(x + y) + tan(x))}")