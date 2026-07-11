package ifba.inf011.p3_2026_1.model.comercial;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.validacao.ProdutoValidador;

// Leaf do Composite
public class Filme extends AbstractProdutoComponent {
	
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

	public String toXML() {
		String xml = "<filme titulo=\"" + this.getTitulo() + "\"/>\n";
		return xml;
	}   
		
}