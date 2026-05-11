package ifba.inf011.builder.interfaces;

// Interface Builder
public interface Builder {
    
    // Reseta o produto atributo interno do builder concreto que armazena o produto
    public void reset();

    // Métodos que modificam o estado interno do produto a ser criado
    public void buildStepA();
    public void buildStepB();
    public void buildStepZ();
    public void buildFeatureB();
}
