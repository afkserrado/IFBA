'''
7) Escreva em Java uma classe Continente. Um continente possui um nome e é composto por um conjunto de países. Forneça os membros de classe a seguir:

a) Construtor que inicialize o nome do continente;
b) Um método que permita adicionar países aos continentes;
c) Um método que retorne a dimensão total do continente;
d) Um método que retorne a população total do continente;
e) Um método que retorne a densidade populacional do continente;
f) Um método que retorne o país com maior população no continente;
g) Um método que retorne o país com menor população no continente;
h) Um método que retorne o país de maior dimensão territorial no continente;
i) Um método que retorne o país de menor dimensão territorial no continente;
j) Um método que retorne a razão territorial do maior pais em relação ao menor país.
'''

from l1fq6.country import Country

class Continent:
    
    # Constructor
    def __init__(self, name):
        if not isinstance(name, str) or not all(c.isalpha() or c.isspace() for c in name) or name.strip() == "":
            raise TypeError("Name must be a string containing only alphabetic characters and spaces, and cannot be only spaces.")
        
        self.__name = name.title()
        self.__countries = []    
        self.__area = 0
        self.__population = 0    

    # Methods
    # Adding a country to the continent
    def addCountry(self, country):
        if not isinstance(country, Country):
            raise TypeError("The argument must be an object of Country class.")
        
        self.__countries.append(country)
        self.__area += country.getArea()
        self.__population += country.getPopulation()

    # Returning the area of the continent
    def area(self):
        return self.__area
    
    # Returning the population of the continent
    def population(self):
        return self.__population
    
    # Checking
    def __checkCountries(self, arg):
        if not self.__countries:
            raise ValueError("No countries in the continent yet.")
        
        if arg == 'density':
            if self.__area == 0:
                raise ValueError("The total area is zero, cannot calculate density.")
                
            return self.__population / self.__area
        
        if arg == 'most_populated': 
            return max(self.__countries, key=lambda c: c.getPopulation())
        
        if arg == 'least_populated':
            return min(self.__countries, key=lambda c: c.getPopulation())
        
        if arg == 'largest':
            return max(self.__countries, key=lambda c: c.getArea())
        
        if arg == 'smallest':
            return min(self.__countries, key=lambda c: c.getArea())
        
        if arg == 'ratio':
            return self.largest().getArea() / self.smallest().getArea()

    # Returning population density
    def density(self):
        return self.__checkCountries('density')
    
    # Returning the most populated country
    def most_populated(self):
        return self.__checkCountries('most_populated')
    
    # Returning the less populated country
    def least_populated(self):
        return self.__checkCountries('least_populated')
    
    # Returning the largest country
    def largest(self):
        return self.__checkCountries('largest')
    
    # Returning the smallest country
    def smallest(self):        
        return self.__checkCountries('smallest')

    # Returning the territorial ratio of the largest country to the smallest one in the continent
    def ratio(self):
        return self.__checkCountries('ratio')
