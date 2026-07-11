package ifba.inf011.p3_2026_1.builder;

import ifba.inf011.p3_2026_1.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;

// Concrete Builder do Builder
public class PacoteBuilder implements ProdutoBuilder {
 
    private Pacote resultado;

    public PacoteBuilder(String titulo) {
        this.resultado = new Pacote(titulo);
    }

    @Override
    public ProdutoBuilder reset(String titulo) {
        this.resultado = new Pacote(titulo);
        return this;
    }

    @Override 
    public ProdutoBuilder adicionarProduto(ProdutoComponent produto) {
        this.resultado.adicionarProduto(produto);
        return this;
    }

    @Override
    public ProdutoComponent build() {
        return this.resultado;
    }

}
