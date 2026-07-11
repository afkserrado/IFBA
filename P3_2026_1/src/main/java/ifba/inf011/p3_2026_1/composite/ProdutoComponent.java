package ifba.inf011.p3_2026_1.composite;

// Interface Component do Composite
public interface ProdutoComponent {
    
    // Métodos comuns a folhas e objetos compostos
    String getTitulo();
    Integer getDuracao();
    Double getPreco();

    // Métodos restritos a objetos compostos

    default void adicionarProduto(ProdutoComponent produto) {
        throw new UnsupportedOperationException("Operação não permitida.");
    }

    default void removerProduto(ProdutoComponent produto) {
        throw new UnsupportedOperationException("Operação não permitida.");
    }

}
