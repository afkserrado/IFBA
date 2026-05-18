package ifba.inf011.s21_2.concrete_creators;

import ifba.inf011.s21_2.concrete_products.Disciplina;
import ifba.inf011.s21_2.concrete_products.Livro;
import ifba.inf011.s21_2.enums.ProductType;
import ifba.inf011.s21_2.interfaces.Creator;
import ifba.inf011.s21_2.interfaces.Product;

// Q1
// Concrete creator
public class ProductCreator implements Creator {

    @Override
    public Product factoryMethod(ProductType type, String codigo, String nome) {
        switch (type) {
            case LIVRO:
                return new Livro(codigo, nome);
            case DISCIPLINA:
                return new Disciplina(codigo, nome);
            default:
                throw new IllegalArgumentException("Tipo de produto inválido: " + type);
        }
    }
}