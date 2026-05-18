package ifba.inf011.s21_2.concrete_builders;

import ifba.inf011.s21_2.concrete_products.Curso;
import ifba.inf011.s21_2.concrete_products.Disciplina;
import ifba.inf011.s21_2.concrete_products.Livro;
import ifba.inf011.s21_2.interfaces.Builder;

// Q2
// Builder concreto
public class CursoBuilder implements Builder<Curso> {

    private Curso curso;

    public CursoBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.curso = new Curso();
    }

    @Override
    public void setCodigo(String codigo) {
        this.curso.setCodigo(codigo);
    }

    @Override
    public void setNome(String nome) {
        this.curso.setNome(nome);
    }

    @Override
    public void addDisciplina(Disciplina disciplina) {
        this.curso.addDisciplina(disciplina);
    }

    @Override
    public void addLivro(Livro livro) {
        this.curso.addLivro(livro);
    }

    @Override
    public Curso getResult() {
        Curso resultado = this.curso;
        return resultado;
    }
}