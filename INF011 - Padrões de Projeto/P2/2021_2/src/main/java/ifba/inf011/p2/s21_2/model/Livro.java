package ifba.inf011.p2.s21_2.model;

import ifba.inf011.p2.s21_2.decorator.AbstractLivroComponent;

// Concrete component do Decorator
public class Livro extends AbstractLivroComponent {

    public Livro(String codigo, String nome, String isbn, double preco) {
        super(codigo, nome, isbn, preco);
    }

    // Herda de Precificavel
    @Override
    public double getPreco() {
        return preco;
    }
}