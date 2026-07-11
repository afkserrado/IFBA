package ifba.inf011.p3_2026_1.builder;

import ifba.inf011.p3_2026_1.composite.ProdutoComponent;

// Interface Builder do Builder
public interface ProdutoBuilder {
    ProdutoBuilder reset(String titulo);
    ProdutoBuilder adicionarProduto(ProdutoComponent produto);
    ProdutoComponent build();
}
