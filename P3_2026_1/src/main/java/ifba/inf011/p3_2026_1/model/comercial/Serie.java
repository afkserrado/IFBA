package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.composite.ProdutoComponent;

// Composite (objeto composto) do Composite
public class Serie extends AbstractProdutoComponent {

	protected Integer temporada;
    protected List<Episodio> episodios;
    
    public Serie(String titulo, Integer temporada) {
    	super(titulo);
        this.temporada = temporada;
    	this.episodios = new ArrayList<>();
    };
    
    public Serie(String titulo, Integer temporada, List<Episodio> episodios) {
		super(titulo);
        this.temporada = temporada;
		this.episodios = new ArrayList<>(episodios);
	};

	public Serie(String titulo, Integer temporada, Episodio... episodios) {
		super(titulo);
        this.temporada = temporada;
		this.episodios = new ArrayList<>(List.of(episodios));
	};

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
        double soma = this.episodios
                          .stream()
                          .mapToDouble(Episodio::getPreco)
                          .sum();
        
        return soma * 0.9;
    }

    @Override
	public void adicionarProduto(ProdutoComponent episodio) {
		if(!(episodio instanceof Episodio)) {
            throw new IllegalArgumentException("Uma série só pode conter episódios.");
        }
        
        this.episodios.add((Episodio) episodio);
	}

	@Override
	public void removerProduto(ProdutoComponent episodio) {
        if(!(episodio instanceof Episodio)) {
            throw new IllegalArgumentException("Uma série só pode conter episódios.");
        }
        
        this.episodios.remove((Episodio) episodio);
	}
        
	public String toXML() {
		String xml = "\t<serie titulo=\"" + this.getTitulo() + "\" temporada=\"" + this.getTemporada() + "\">\n";
		for(Episodio episodio : this.episodios)
			xml += episodio.toXML();
		return xml + "\t</serie>\n";
		
	}    

}