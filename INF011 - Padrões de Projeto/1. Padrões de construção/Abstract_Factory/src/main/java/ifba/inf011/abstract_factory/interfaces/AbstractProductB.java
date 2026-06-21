package ifba.inf011.abstract_factory.interfaces;

// Declara interface para a família de produtos B
// Exemplo: Sofa
public interface AbstractProductB {
    String operationB();
    
    // Produto B pode colaborar com Produto A da mesma família
    String collaborateWith(AbstractProductA productA);
}