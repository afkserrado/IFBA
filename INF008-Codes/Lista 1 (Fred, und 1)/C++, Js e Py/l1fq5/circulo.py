'''
5) Escreva em Java uma classe que represente um círculo no plano cartesiano. Forneça os seguintes membros de classe:

a) Um construtor que receba o raio e um ponto (o centro do círculo);
b) Um construtor que receba o raio e posicione o círculo na origem do espaço cartesiano;
c) Métodos de acesso ao atributo raio do círculo;
d) Métodos inflar e desinflar, que, respectivamente, aumentam e diminuem o raio do círculo de um dado valor;
e) Métodos sobrecarregados inflar e desinflar, que, respectivamente, aumentam e diminuem o raio do círculo de uma unidade;
h) Métodos sobrecarregados mover, que:
    i) por default (sem parâmetros) levam o círculo para a origem do espaço 2D;
    ii) movem o círculo para um local indicado por dois parâmetros do tipo double (indicando o valor de abcissa e ordenada do ponto para onde o círculo se move);
    iii) movem o círculo para o local indicado por outro ponto;
f) Método que retorna a área do círculo.
'''

from ponto import Ponto
from math import pi

class Circulo:
    # Representa um círculo no plano cartesiano.

    # __init__ define o construtor
    # Todos os métodos, construtores ou não, começam com o parâmetro self

    # Construtores
    def __init__(self, raio, centro = None):
        
        if centro is None:
            centro = Ponto(0,0)
        
        if not  (isinstance(raio, (int, float)) and 
            raio >= 0 and 
            isinstance(centro, Ponto)):

            raise TypeError("Parâmetros inválidos.")

        self.__raio = raio
        self.__centro = centro
        
    # Acessando o raio
    def getRaio(self):
        return self.__raio
    
    # Acessando o centro
    def getCentro(self):
        return (self.__centro.getX(), self.__centro.getY())    
    
    # Aumentando raio
    def inflar(self, valor = 1):
        if not isinstance(valor, (int, float)):
            raise TypeError("O valor deve ser int ou float.")
        
        self.__raio += valor
        
    # Diminuindo raio
    def desinflar(self, valor = 1):
        if not isinstance(valor, (int, float)):
            raise TypeError("O valor deve ser int ou float.")
        
        self.__raio -= valor
        
    # Movendo o círculo
    def mover(self, *args):

        # Método 1
        if len(args) == 0:
            self.__centro = Ponto(0,0)

        # Método 2
        elif len(args) == 2 and all(isinstance(el, (int, float)) for el in args):
            self.__centro = Ponto(args[0], args[1])

        # Método 3
        elif len(args) == 1 and isinstance(args[0], Ponto):
            self.__centro = args[0]

        else:
            raise TypeError("Indicar 1 parâmetro (ponto), 2 parâmetros (x,y) ou nenhum. Todos os parâmetros devem ser float.")
        
    # Calculando a área do círculo
    def area(self):
        return pi * self.__raio ** 2
    