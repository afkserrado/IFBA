'''
4) Escreva em Java uma classe que represente uma reta (y = ax + b).
Forneça os seguintes membros de classe:

a) Construtores sobrecarregados que criem uma reta a partir de:
    i) Dois valores, representando o coeficiente angular e o coeficiente linear da reta;
    ii) Dois pontos;

b) Métodos de acesso para o coeficiente angular e para o coeficiente linear da reta;
c) Um método que verifique se um ponto dado pertence à reta;
d) Um método que gere e retorne a representação String da reta;
e) Um método que, dada uma outra reta, retorne o ponto de interseção da reta dada ou null se as retas forem paralelas.
'''

class Reta:
    # Representa uma reta no plano cartesiano.

    # __init__ define o construtor
    # Todos os métodos, construtores ou não, começam com o parâmetro self

    def __init__(self, *parametros): 
        if len(parametros) == 2: 
            # Construtor 1: coeficientes angular e linear
            self.a, self.b = parametros

        elif len(parametros) == 4: 
            # Construtor 2: dois pontos
            x1, y1, x2, y2 = parametros
            self.a = (y2 - y1) / (x2 - x1) # Coeficiente angular
            self.b = y2 - self.a * x2 # Coeficiente linear
        
        else:
            raise TypeError("Use 2 parâmetros (a, b) ou 4 parâmetros (x1, x2, y1, y4).")

    # b)
    def getCoefAngular(self):
        return self.a
    
    def getCoefLinear(self):
        return self.b
    
    # c)
    def contemPonto(self, x, y):
        resY = self.a * x + self.b
        if (resY == y):
            print(f"O ponto ({x},{y}) pertence à reta.")
        else:
            print(f"O ponto ({x},{y}) não pertence à reta.")

    # d)
    def imprimeReta(self):
        return f"y ={self.a: .1f}x +{self.b: .1f}"

    # e)
    def contemReta(self, reta):
        a2 = reta.getCoefAngular()
        b2 = reta.getCoefLinear()

        if (self.a == a2):
            return None
        
        x = (b2 - self.b) / (a2 - self.a)
        y = self.a * x + self.b

        # Retorna uma tupla (lista imutável)
        return (x, y)
