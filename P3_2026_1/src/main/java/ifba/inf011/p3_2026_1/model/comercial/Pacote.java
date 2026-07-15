package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.PlaylistItem;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

// Composite (objeto composto) do Composite
// Product do Builder
// Concrete Element do Visitor
public class Pacote extends AbstractProdutoComponent implements PlaylistItem {

	private final List<ProdutoComponent> produtos;
	private Double desconto;
	
	public Pacote(String titulo, Double desconto) {
		super(titulo);
		
		validarDesconto(desconto);

		this.desconto = desconto;
		this.produtos = new ArrayList<>();
	}
	
	public Pacote(String titulo, Double desconto, List<ProdutoComponent> produtos) {
		this(titulo, desconto);

		ProdutoValidador.validarLista(produtos);

		this.produtos.addAll(produtos);
	}

	public Pacote(String titulo, Double desconto, ProdutoComponent... produtos) {
		this(titulo, desconto);

		ProdutoValidador.validarLista(produtos);

		this.produtos.addAll(List.of(produtos));
	}

	public void alterarTitulo(String titulo) {
		setTitulo(titulo);
	}

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

		return soma * (1 - this.desconto / 100);
	}

	public Double consultarDesconto() {
		return this.desconto;
	}

	public void alterarDesconto(Double desconto) {
		validarDesconto(desconto);
		this.desconto = desconto;
	}

	@Override
	public void adicionarProduto(ProdutoComponent produto) {	
		ProdutoValidador.validarProduto(produto);		
		this.produtos.add(produto);
	}

	@Override
	public void removerProduto(ProdutoComponent produto) {
		this.produtos.remove(produto);
	}

	// Para implementação do Visitor
	@Override
	public void accept(VisitorPlaylist visitor) {
		visitor.visit(this);

		for(ProdutoComponent produto : produtos) {
			if(produto instanceof PlaylistItem item) {
				item.accept(visitor);
			}
		}
	}

	// Se o desconto for aplicável a outros tipos, extrair esse método
	// para a classe ProdutoValidador
	private static void validarDesconto(Double desconto) {
		ProdutoValidador.validarNaoNegativo(desconto);

		if(desconto > 100) {
			throw new IllegalArgumentException("O desconto não pode ser maior que 100%.");
		}
	}

}