package ifba.inf011.s21_2.concrete_products;

import ifba.inf011.s21_2.interfaces.Product;

// Concrete product
public class Livro extends Product {

    public Livro(String codigo, String nome) {
        super(codigo, nome);
    }

    @Override
    public String toString() {
        return "Livro{" + super.toString() + "}";
    }
}