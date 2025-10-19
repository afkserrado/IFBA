from reta import Reta

# Main
reta1 = Reta(1, 0)
reta2 = Reta(0, 1, 1, 3)

# Imprimindo os coeficientes angular e linear da reta
print("Coeficientes angular e linear")
print(f"a1 ={reta1.getCoefAngular(): .1f}, b1 ={reta1.getCoefLinear(): .1f}")
print(f"a2 ={reta2.getCoefAngular(): .1f}, b2 ={reta2.getCoefLinear(): .1f}")
print()

# Verificando se um ponto pertence à reta
ponto = (0, 0)
x, y = ponto
print(f"A reta 1 contém o ponto {ponto}?")
reta1.contemPonto(x, y)

print(f"A reta 2 contém o ponto {ponto}?")
reta2.contemPonto(x, y)
print()

# Imprimindo as retas
print("Retas")
print(f"Reta 1: {reta1.imprimeReta()}")
print(f"Reta 2: {reta2.imprimeReta()}")
print()

# Verificando há interseção entre as retas
a1, b1 = reta1.getCoefAngular(), reta1.getCoefLinear()

a2, b2 = reta2.getCoefAngular(), reta2.getCoefLinear()

reta3 = Reta(1, 1)
a3, b3 = reta3.getCoefAngular(), reta3.getCoefLinear()

intersecao = reta1.contemReta(reta3)

if intersecao is None:
    print("Não há interseção.")
else:
    print(f"Interseção entre as retas 1 ({a1: 1f}, {b1: 1f}) e 3 ({a3: .1f}, {b3:.1f}):")
    print(f"({intersecao[0]: .1f},{intersecao[1]:.1f})")

intersecao = reta2.contemReta(reta3)

if intersecao is None:
    print("Não há interseção.")
else:
    print(f"Interseção entre as retas 2 ({a2:.1f}, {b2:.1f}) e 3 ({a3:.1f}, {b3:.1f}):", end=' ')
    print(f"({intersecao[0]: .1f},{intersecao[1]:.1f})")
