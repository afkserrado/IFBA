package ifba.inf011.s21_2.interfaces;

import ifba.inf011.s21_2.enums.ProductType;

// Q1
// Interface creator
public interface Creator {
    public abstract Product factoryMethod(ProductType type, String codigo, String nome);
}