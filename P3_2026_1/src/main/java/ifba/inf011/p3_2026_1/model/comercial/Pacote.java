package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;

// Composite (objeto composto) do Composite
public class Pacote extends AbstractProdutoComponent implements PlaylistItem {

	protected List<ProdutoComponent> produtos;
	
	public Pacote(String titulo) {
		super(titulo);
		this.produtos = new ArrayList<>();
	};
	
	public Pacote(String titulo, List<ProdutoComponent> produtos) {
		super(titulo);
		this.produtos = new ArrayList<>(produtos);
	};

	public Pacote(String titulo, ProdutoComponent... produtos) {
		super(titulo);
		this.produtos = new ArrayList<>(List.of(produtos));
	};

	@Override
	public Integer getDuracao() {
		return this.produtos
				   .stream()
				   .mapToInt(produto -> produto.getDuracao())
				   .sum();
	} 

	@Override
	public Double getPreco() {
		double soma = this.produtos
						  .stream()
						  .mapToDouble(ProdutoComponent::getPreco)
						  .sum();

		return soma * 0.9;
	}

	@Override
	public void adicionarProduto(ProdutoComponent produto) {
		this.produtos.add(produto);
	}

	@Override
	public void removerProduto(ProdutoComponent produto) {
		this.produtos.remove(produto);
	}

	@Override
	public String toXML() {
		// String produtosXML = this.produtos.stream()
		// 		.map(Filme::toXML)
		// 		.collect(Collectors.joining());
		// return "<pacote titulo=\"" + this.getTitulo() + "\">\n" 
		// 	+ produtosXML 
		// 	+ "</pacote>\n";

		return ""; // Só para teste
	}

	@Override
	public Double getBandwidth(Double bandPerSecond) {
		return this.getDuracao() * bandPerSecond;
	}    

}