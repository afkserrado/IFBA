package ifba.inf011.p3_2026_1.model.comercial;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.PlaylistItem;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

// Leaf do Composite
// Concrete Element do Visitor
public class Filme extends AbstractProdutoComponent implements PlaylistItem {
	
    private Double preco;
    private Timeline timeline;

	public Filme(String titulo, Double preco, Timeline timeline) {
    	super(titulo);

		ProdutoValidador.validarPreco(preco);
		ProdutoValidador.validarTimeline(timeline);

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