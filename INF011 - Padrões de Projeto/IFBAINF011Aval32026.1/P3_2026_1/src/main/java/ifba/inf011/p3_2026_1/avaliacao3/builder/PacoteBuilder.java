package ifba.inf011.p3_2026_1.avaliacao3.builder;

import ifba.inf011.p3_2026_1.avaliacao3.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;

// Interface Builder do Builder
public interface PacoteBuilder {
    PacoteBuilder reset();
    PacoteBuilder definirTitulo(String titulo);
    PacoteBuilder definirDesconto(Double desconto);
    PacoteBuilder adicionarProduto(ProdutoComponent produto);
    Pacote build();
}
