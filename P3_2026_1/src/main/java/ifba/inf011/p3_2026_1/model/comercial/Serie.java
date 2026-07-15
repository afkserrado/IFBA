package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;

// Composite (objeto composto) do Composite
public class Serie extends AbstractProdutoComponent {

	private Integer temporada;
    private List<Episodio> episodios;
    
    public Serie(String titulo, Integer temporada) {
    	super(titulo);

        ProdutoValidador.validarNaoNegativo(temporada);

        this.temporada = temporada;
    	this.episodios = new ArrayList<>();
    }
    
    public Serie(String titulo, Integer temporada, List<Episodio> episodios) {
		this(titulo, temporada);

        ProdutoValidador.validarLista(episodios);

		this.episodios = new ArrayList<>(episodios);
	}

	public Serie(String titulo, Integer temporada, Episodio... episodios) {
		this(titulo, temporada);

        ProdutoValidador.validarLista(episodios);

		this.episodios = new ArrayList<>(List.of(episodios));
	}

    public Integer getTemporada() {
    	return this.temporada;
    }

    @Override
    public Integer getDuracao() {
        return this.episodios
                   .stream()
                   .mapToInt(Episodio::getDuracao)
                   .sum();
    }

    @Override
    public Double getPreco() {
        return this.episodios
                   .stream()
                   .mapToDouble(Episodio::getPreco)
                   .sum();
    }

    @Override
	public void adicionarProduto(ProdutoComponent produto) {
		if(!(produto instanceof Episodio episodio)) {
            throw new IllegalArgumentException("Uma série só pode conter episódios.");
        }
        
        this.episodios.add(episodio);
	}

	@Override
	public void removerProduto(ProdutoComponent produto) {
        if(!(produto instanceof Episodio episodio)) {
            throw new IllegalArgumentException("Uma série só pode conter episódios.");
        }
        
        this.episodios.remove(episodio);
	}
        
	public String toXML() {
		String xml = "\t<serie titulo=\"" + this.getTitulo() + "\" temporada=\"" + this.getTemporada() + "\">\n";
		for(Episodio episodio : this.episodios)
			xml += episodio.toXML();
		return xml + "\t</serie>\n";
		
	}    

}