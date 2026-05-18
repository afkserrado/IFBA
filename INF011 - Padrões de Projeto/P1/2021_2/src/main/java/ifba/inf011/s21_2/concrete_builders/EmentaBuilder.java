package ifba.inf011.s21_2.concrete_builders;

import ifba.inf011.s21_2.concrete_products.Disciplina;
import ifba.inf011.s21_2.concrete_products.Ementa;
import ifba.inf011.s21_2.concrete_products.Livro;
import ifba.inf011.s21_2.interfaces.Builder;

// Q2
// Builder concreto
public class EmentaBuilder implements Builder<Ementa> {

    private Ementa ementa;

    public EmentaBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.ementa = new Ementa();
    }

    @Override
    public void setCodigo(String codigo) {
        this.ementa.setCodigoCurso(codigo);
    }

    @Override
    public void setNome(String nome) {
        this.ementa.setNomeCurso(nome);
    }

    @Override
    public void addDisciplina(Disciplina disciplina) {
        this.ementa.addDisciplina(disciplina);
    }

    @Override
    public void addLivro(Livro livro) {
        this.ementa.addLivro(livro);
    }

    @Override
    public Ementa getResult() {
        Ementa resultado = this.ementa;
        this.reset();
        return resultado;
    }
}