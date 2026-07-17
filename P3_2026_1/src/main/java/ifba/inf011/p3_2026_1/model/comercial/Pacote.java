package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.avaliacao3.composite.AbstractProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.util.ValidadorUtil;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;

// Composite (objeto composto) do Composite
// Product do Builder
// Concrete Element do Visitor
public class Pacote extends AbstractProdutoComponent implements PlaylistItem {

	private static final String MSG_DESCONTO_INVALIDO =
        "O desconto do pacote não pode ser nulo ou negativo.";
    private static final String MSG_LISTA_PRODUTOS_INVALIDA =
        "A lista de produtos do pacote não pode ser nula.";
    private static final String MSG_ARRAY_PRODUTOS_INVALIDO =
        "O array de produtos do pacote não pode ser nulo.";
    private static final String MSG_PRODUTO_INVALIDO =
        "O produto do pacote não pode ser nulo.";
    private static final String MSG_DESCONTO_MAIOR_100 =
        "O desconto do pacote não pode ser maior que 100%.";

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
		ValidadorUtil.validarColecao(produtos, MSG_LISTA_PRODUTOS_INVALIDA);
		this.produtos.addAll(produtos);
	}

	public Pacote(String titulo, Double desconto, ProdutoComponent... produtos) {
		this(titulo, desconto);
		ValidadorUtil.validarArray(produtos, MSG_ARRAY_PRODUTOS_INVALIDO);
		this.produtos.addAll(List.of(produtos));
	}

	public void alterarTitulo(String titulo) {
		setTitulo(titulo);
	}

	public List<ProdutoComponent> getProdutos() {
		return List.copyOf(produtos);
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
		ValidadorUtil.validarObjeto(produto, MSG_PRODUTO_INVALIDO);    
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
	}

	private static void validarDesconto(Double desconto) {
		
		ValidadorUtil.validarNaoNegativo(desconto, MSG_DESCONTO_INVALIDO);

		if(desconto > 100) {
            throw new IllegalArgumentException(MSG_DESCONTO_MAIOR_100);
        }
	}

}