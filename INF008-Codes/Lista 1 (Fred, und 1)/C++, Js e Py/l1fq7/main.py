from l1fq6.country import Country
from l1fq7.continent import Continent

def main():
    # Criando países
    brazil = Country("BRA", "Brasil", 8515767)
    brazil.setPopulation(214000000)

    argentina = Country("ARG", "Argentina", 2780400)
    argentina.setPopulation(46000000)

    uruguay = Country("URY", "Uruguai", 176215)
    uruguay.setPopulation(3500000)

    paraguay = Country("PRY", "Paraguay", 406752)
    paraguay.setPopulation(7150000)

    # Criando continente
    south_america = Continent("América do Sul")

    # Adicionando países ao continente
    south_america.addCountry(brazil)
    south_america.addCountry(argentina)
    south_america.addCountry(uruguay)
    south_america.addCountry(paraguay)

    # Testando área total
    print(f"Área total do continente: {south_america.area():,.0f} km²")

    # Testando população total
    print(f"População total do continente: {south_america.population():,}")

    # Testando densidade populacional
    print(f"Densidade populacional: {south_america.density():.2f} hab/km²")

    # País mais populoso
    most_pop = south_america.most_populated()
    print(f"País mais populoso: {most_pop.getName()} ({most_pop.getPopulation():,} habitantes)")

    # País menos populoso
    least_pop = south_america.least_populated()
    print(f"País menos populoso: {least_pop.getName()} ({least_pop.getPopulation():,} habitantes)")

    # País com maior área
    largest = south_america.largest()
    print(f"País com maior área: {largest.getName()} ({largest.getArea():,.0f} km²)")

    # País com menor área
    smallest = south_america.smallest()
    print(f"País com menor área: {smallest.getName()} ({smallest.getArea():,.0f} km²)")

    # Razão territorial entre maior e menor país
    ratio = south_america.ratio()
    print(f"Razão territorial (maior/menor país): {ratio:.2f}")

if __name__ == "__main__":
    main()
