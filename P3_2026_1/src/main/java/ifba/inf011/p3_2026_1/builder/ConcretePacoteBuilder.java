package ifba.inf011.p3_2026_1.builder;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;

// Concrete Builder do Builder
public class ConcretePacoteBuilder implements PacoteBuilder {
 
    private String titulo;
    private Double desconto;
    private List<ProdutoComponent> produtos;

    public ConcretePacoteBuilder() {
        reset();
    }

    @Override
    public PacoteBuilder reset() {
        this.titulo = null;
        this.desconto = null;
        this.produtos = new ArrayList<>();
        return this;
    }

    @Override
    public PacoteBuilder definirTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    @Override
    public PacoteBuilder definirDesconto(Double desconto) {
        this.desconto = desconto;
        return this;
    }

    @Override 
    public PacoteBuilder adicionarProduto(ProdutoComponent produto) {
        this.produtos.add(produto);
        return this;
    }

    // O reset não foi inserido no build() para permitir a criação de novas
    // instâncias de Pacote a partir do mesmo estado acumulado no builder.
    @Override
    public Pacote build() {
        return new Pacote(
            this.titulo,
            this.desconto,
            this.produtos
        );
    }

}
