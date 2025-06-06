'''
7.Dado um pais A, com 5.000.000 de habitantes e uma taxa de natalidade de 3% ao ano, e um pais B com 7.000.000 de habitantes e uma taxa de natalidade de 2% ao ano. Calcular e imprimir o tempo necessário para que a população do pais A ultrapasse a população do pais B.
'''

popA = 5000000
popB = 7000000
txA = 0.03
txB = 0.02

anos = 0

while popA <= popB:
    popA *= (1 + txA)
    popB *= (1 + txB)

    anos += 1

#print(f"PopA: {popA: .0f} | PopB: {popB: .0f}")
print(f"A populacao de A ultrapassara a de B em {anos} anos.")

