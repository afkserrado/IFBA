'''
6) Escreva uma classe que represente um país. Um país é representado através dos atributos: código ISO 3166-1 (ex.: BRA), nome (ex.: Brasil), população (ex.: 193.946.886) e a sua dimensão em Km² (ex.: 8.515.767,049).

Além disso, cada país mantém uma lista de outros países com os quais ele faz fronteira. Escreva a classe em Java e forneça os seus membros a seguir:

a) Construtor que inicialize o código ISO, o nome e a dimensão do país;
b) Métodos de acesso (getter/setter) para as propriedades código ISO, nome, população e dimensão do país;
c) Um método que permita verificar se dois objetos representam o mesmo país (igualdade semântica). Dois países são iguais se tiverem o mesmo código ISO;
d) Um método que informe se outro país é limítrofe do país que recebeu a mensagem;
e) Um método que retorne a densidade populacional do país;
f) Um método que receba um país como parâmetro e retorne a lista de vizinhos comuns aos dois países.

Considere que um país tem no máximo 40 outros países com os quais ele faz fronteira.
'''

class Country:

    # Constructor
    def __init__(self, code, name, area):
        self.setCode(code)
        self.setName(name)
        self.setArea(area)
        self.setPopulation(0)
        self.__borders = [] # Initializing the borders list

    # Getters
    def getCode(self):
        return self.__code
    
    def getName(self):
        return self.__name
    
    def getPopulation(self):
        return self.__population
    
    def getArea(self):
        return self.__area
    
    # Setters
    def setCode(self, code):
        if len(code) != 3 or not isinstance(code, str) or not code.isalpha():
            raise TypeError("Code must be a string with exactly three alphabetic characters.")
        
        self.__code = code.upper()

    def setName(self, name):
        if not isinstance(name, str) or not all(c.isalpha() or c.isspace() for c in name) or name.strip() == "":
            raise TypeError("Name must be a string containing only alphabetic characters and spaces, and cannot be only spaces.")
        
        self.__name = name.title()

    def setPopulation(self, population):
        if not isinstance(population, int) or population < 0:
            raise TypeError("Population must be an integer number greater than or equal to zero.")
        
        self.__population = population

    def setArea(self, area):
        if not isinstance(area, (int, float)) or area <= 0: 
            raise TypeError("Area must be an integer or float number greater than zero.")
        
        self.__area = area

    def setBorder(self, country):
        self.__iscountry(country)
        
        if len(self.__borders) >= 40:
            print(f"{self.getName()} has already the maximum number of neighbors.")
            return
        
        if country not in self.__borders:
            self.__borders.append(country)
        else:
            print(f"{country.getName()} is already a border.")

    # Other methods
    def compareCountries(self, country):
        self.__iscountry(country)

        # Comparing the codes
        if self.getCode() == country.getCode():
            return "Both countries are the same."
        else:
            return "Both countries are different."
        
    def isborder(self, country):
        self.__iscountry(country)

        if country in self.__borders:
            print(f"{self.getName()} and {country.getName()} are neighbors.")
        else:
            print(f"{self.getName()} and {country.getName()} aren't neighbors.")
        
    def popDensity(self):
        try:
            return self.__population / self.__area

        except ZeroDivisionError:
            print("Impossible to divide population by area: area cannot be zero.")

        except Exception as error:
            print(f"Unexpected error: {error}")

    def commonNeighbors(self, country):
        self.__iscountry(country)
        return list(set(self.__borders).intersection(country.__borders))
        
    # Auxiliar methods
    def __iscountry(self, country):
        if not isinstance(country, Country):
            raise TypeError("Country must be a country object.")