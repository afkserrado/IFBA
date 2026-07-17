package ifba.inf011.p3_2026_1.model.comercial;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.util.ValidadorUtil;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;

// Leaf do Composite
// Concrete Element do Visitor
public class Filme extends AbstractProdutoComponent implements PlaylistItem {
    
    private static final String MSG_PRECO_INVALIDO =
        "O preço do filme não pode ser nulo ou negativo.";
    private static final String MSG_TIMELINE_INVALIDA =
        "A timeline do filme não pode ser nula.";

    private Double preco;
    private Timeline timeline;

    public Filme(String titulo, Double preco, Timeline timeline) {
        super(titulo);

        ValidadorUtil.validarNaoNegativo(preco, MSG_PRECO_INVALIDO);
        ValidadorUtil.validarObjeto(timeline, MSG_TIMELINE_INVALIDA);

        this.preco = preco;       
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

    // Para implementação do Visitor
    @Override
    public void accept(VisitorPlaylist visitor) {
        visitor.visit(this);
    }
}