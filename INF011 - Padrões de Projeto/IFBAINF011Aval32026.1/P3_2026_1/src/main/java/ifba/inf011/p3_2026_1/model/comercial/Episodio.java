package ifba.inf011.p3_2026_1.model.comercial;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.util.ValidadorUtil;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

// Leaf do Composite
// Concrete Element do Visitor
public class Episodio extends AbstractProdutoComponent {
    
    private static final String MSG_PRECO_INVALIDO =
        "O preço do episódio não pode ser nulo ou negativo.";
    private static final String MSG_NUMERO_INVALIDO =
        "O número do episódio não pode ser nulo ou negativo.";
    private static final String MSG_TIMELINE_INVALIDA =
        "A timeline do episódio não pode ser nula.";

    private Double preco;
    private Timeline timeline;
    private Integer numero;

    public Episodio(String titulo, Double preco, Integer numero, Timeline timeline) {
        super(titulo);

        ValidadorUtil.validarNaoNegativo(preco, MSG_PRECO_INVALIDO);
        ValidadorUtil.validarNaoNegativo(numero, MSG_NUMERO_INVALIDO);
        ValidadorUtil.validarObjeto(timeline, MSG_TIMELINE_INVALIDA);

        this.preco = preco;
        this.numero = numero;
        this.timeline = timeline;
    }
    
    @Override
    public Double getPreco() {
        return this.preco; 
    }
    
    @Override
    public Integer getDuracao() { 
        return this.timeline.getDurationInSeconds();
    }

    public Integer getNumero() {
        return this.numero;
    }

    // Para implementação do Visitor
    @Override
    public void accept(VisitorPlaylist visitor) {
        visitor.visit(this);
    }
}