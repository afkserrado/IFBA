package ifba.inf011.builder.interfaces;

// Interface Builder para construção encadeada
public interface FluentBuilder {
    
    // Reseta o produto atributo interno do builder concreto que armazena o produto
    public FluentBuilder reset();

    // Métodos que modificam o estado interno do produto a ser criado
    public FluentBuilder buildStepA();
    public FluentBuilder buildStepB();
    public FluentBuilder buildStepZ();
    public FluentBuilder buildFeatureB();
}
