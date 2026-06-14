package ifba.inf011.p2.s21_2.model;

import java.util.Collections;
import java.util.Set;

import ifba.inf011.p2.s21_2.composite.Component;

// Leaf do Composite
// Modelo original ajustado para torná-lo compatível com o padrão Composite
public class Disciplina extends Produto implements Component {

    private double preco;
    private int chTotal;
    private int chCumprida;

    public Disciplina(String codigo, String nome, double preco, int chTotal, int chCumprida) {
        super(codigo, nome);
        this.preco = preco;
        this.chTotal = chTotal;
        this.chCumprida = chCumprida;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public int getCHCumprida() {
        return chCumprida;
    }

    @Override
    public int getCHTotal() {
        return chTotal;
    }

    @Override
    public double getPctCHCumprida() {
        if(chTotal == 0) return 0.0;
        return ((double) chCumprida) / chTotal;
    }

    @Override
    public Set<Component> getDisciplinas() {
        // Retorna um Set imutável contendo uma referência para o objeto atual
        return Collections.singleton(this);
    }
}