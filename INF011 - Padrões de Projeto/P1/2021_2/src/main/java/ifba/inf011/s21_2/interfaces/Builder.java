package ifba.inf011.s21_2.interfaces;

import ifba.inf011.s21_2.concrete_products.Disciplina;
import ifba.inf011.s21_2.concrete_products.Livro;

// Q2
// Interface builder
public interface Builder<T> {
    void reset();
    void setCodigo(String codigo);
    void setNome(String nome);
    void addDisciplina(Disciplina disciplina);
    void addLivro(Livro livro);
    T getResult();
}