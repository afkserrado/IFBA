package ifba.inf011.abstract_factory.interfaces;

// Declara interface para criação de cada produto abstrato
public interface AbstractFactory {
    AbstractProductA createProductA(); // Exemplo: Chair
    AbstractProductB createProductB(); // Exemplo: Sofa
}