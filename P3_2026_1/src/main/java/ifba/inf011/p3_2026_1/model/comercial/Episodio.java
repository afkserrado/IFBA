package ifba.inf011.p3_2026_1.model.comercial;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;

// Leaf do Composite
public class Episodio extends AbstractProdutoComponent {
	
    private Double preco;
    private Timeline timeline;
    private Integer numero;

	public Episodio(String titulo, Double preco, Integer numero, Timeline timeline) {
    	super(titulo);

		ProdutoValidador.validarPreco(preco);
		ProdutoValidador.validarNaoNegativo(numero);
		ProdutoValidador.validarTimeline(timeline);

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

	public String toXML() {
		String xml = "<episodio titulo=\"" + this.getTitulo() + "\" numero=\"" + this.getNumero() + "\"/>\n";
		return xml;
	}  
	 	
}