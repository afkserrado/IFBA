'''
1. Usar a biblioteca correta e as funções matemáticas do Python, quando necessárias,
para resolver as seguintes expressões:
(a) y = 4^3 − 2^2
(b) x = sen(2) − cos(4.2)
(c) z = cos(sen(3.7) − tan(1.3))
(d) Resto da divisão de 26 por 4.
(e) Converter x = 46.2° para radianos.
(f) Converter y = 3.1 rad para graus.
'''

from math import sin, cos, tan, radians, degrees

# (a)
y = 4**3 - 2**2
print(f"y = {y}")

# (b)
x = sin(2) - cos(4.2)
print(f"x = {x:.2f}")

# (c)
z = cos(sin(3.7) - tan(1.3))
print(f"z = {z:.2f}")

# (d)
print(26%4)

# (e)
print(f"x = {radians(46.2)} radianos")

# (f)
print(f"y = {degrees(3.1)} graus")