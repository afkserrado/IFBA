package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;

// Composite (objeto composto) do Composite
// Concrete Element do Visitor
public class Serie extends AbstractProdutoComponent implements PlaylistItem {

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

    public List<Episodio> getEpisodios() {
        return List.copyOf(episodios);
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
        
	// Para implementação do Visitor
	@Override
	public void accept(VisitorPlaylist visitor) {
		visitor.visit(this);
	}

}