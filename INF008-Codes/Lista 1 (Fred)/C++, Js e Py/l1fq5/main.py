from circulo import Circulo
from ponto import Ponto

def main():
    print("=== Testando construtores ===")
    # Construtor com raio apenas (centro na origem)
    c1 = Circulo(5)
    print(f"Círculo 1: raio={c1.getRaio()}, centro={c1.getCentro()}")

    # Construtor com raio e centro
    c2 = Circulo(3, Ponto(2, 4))
    print(f"Círculo 2: raio={c2.getRaio()}, centro={c2.getCentro()}")

    print("\n=== Testando inflar e desinflar ===")
    c1.inflar(2)  # aumenta em 2
    print(f"Círculo 1 inflado: raio={c1.getRaio()}")
    c2.desinflar()  # diminui 1 (valor padrão)
    print(f"Círculo 2 desinflado: raio={c2.getRaio()}")

    print("\n=== Testando mover ===")
    c1.mover()  # para a origem
    print(f"Círculo 1 movido para origem: centro={c1.getCentro()}")

    c2.mover(5, 6)  # mover por coordenadas
    print(f"Círculo 2 movido para (5,6): centro={c2.getCentro()}")

    c1.mover(Ponto(1,1))  # mover por ponto
    print(f"Círculo 1 movido para Ponto(1,1): centro={c1.getCentro()}")

    print("\n=== Testando área ===")
    print(f"Área do Círculo 1: {c1.area():.2f}")
    print(f"Área do Círculo 2: {c2.area():.2f}")

    print("\n=== Forçando erros ===")
    try:
        Circulo(-1)  # raio negativo
    except TypeError as e:
        print(f"Erro esperado: {e}")

    try:
        Circulo(3, "não é ponto")  # centro inválido
    except TypeError as e:
        print(f"Erro esperado: {e}")

    try:
        c1.inflar("dez")  # valor inválido
    except TypeError as e:
        print(f"Erro esperado: {e}")

    try:
        c2.mover("x", "y")  # argumentos inválidos
    except TypeError as e:
        print(f"Erro esperado: {e}")

if __name__ == "__main__":
    main()
