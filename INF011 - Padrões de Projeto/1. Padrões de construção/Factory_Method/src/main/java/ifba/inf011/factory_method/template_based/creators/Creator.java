package ifba.inf011.factory_method.template_based.creators;

import ifba.inf011.factory_method.template_based.interfaces.Product;

// Creator genérico: não precisa criar uma subclasse Creator
// para cada tipo concreto de produto.
//
// T extends Product:
// - T é um tipo genérico.
// - extends Product restringe T para apenas classes
//   que implementem/estendam Product.
// - Isso garante ao compilador que qualquer T possuirá
//   os comportamentos definidos na interface Product.
public class Creator<T extends Product> {

    // Class<T> é uma metaclasse:
    // representa a classe concreta de T em tempo de execução.
    //
    // Exemplos:
    // ProductA.class -> Class<ProductA>
    // ProductB.class -> Class<ProductB>
    //
    // Como T extends Product, o compilador sabe que
    // productClass sempre representará um Product válido.
    private final Class<T> productClass;

    // Recebe a metaclasse do produto concreto.
    //
    // Exemplo:
    // new Creator<>(ProductA.class)
    //
    // Aqui:
    // T = ProductA
    // productClass = ProductA.class
    public Creator(Class<T> productClass) {
        this.productClass = productClass;
    }

    // Factory Method implementado via reflection.
    //
    // Reflection permite instanciar dinamicamente
    // a classe concreta armazenada em productClass,
    // eliminando a necessidade de subclassing.
    //
    // Equivalente conceitual:
    // return new ProductA();
    //
    // Mas decidido dinamicamente em runtime.
    public T create() {
        try {
            // Reflection:
            // 1. Obtém o construtor sem parâmetros
            // 2. Cria uma nova instância da classe concreta
            return productClass.getDeclaredConstructor().newInstance();

        } catch (Exception e) {

            // Qualquer erro de reflection é encapsulado
            // em RuntimeException.
            throw new RuntimeException(
                "Erro ao criar produto: " + productClass.getName(),
                e
            );
        }
    }

    public void anOperation() {

        // Como T extends Product,
        // o retorno de create() pode ser tratado
        // com segurança como Product.
        Product product = create();

        // O compilador sabe que doSomething() existe,
        // pois todo T implementa Product.
        product.doSomething();
    }
}