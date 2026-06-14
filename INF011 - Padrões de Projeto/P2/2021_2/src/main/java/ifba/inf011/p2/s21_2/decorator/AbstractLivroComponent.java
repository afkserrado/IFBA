package ifba.inf011.p2.s21_2.decorator;

import ifba.inf011.p2.s21_2.model.Produto;

// Component abstrato
public abstract class AbstractLivroComponent extends Produto implements LivroComponent {
    
    protected String isbn;
    protected double preco;

    public AbstractLivroComponent(String codigo, String nome, String isbn, double preco) {
        super(codigo, nome);
        this.isbn = isbn;
        this.preco = preco;
    }

    public String getIsbn() {
        return isbn;
    }
}
