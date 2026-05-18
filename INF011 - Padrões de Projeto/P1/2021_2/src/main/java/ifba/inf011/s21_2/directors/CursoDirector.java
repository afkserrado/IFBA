package ifba.inf011.s21_2.directors;

import ifba.inf011.s21_2.concrete_products.Disciplina;
import ifba.inf011.s21_2.concrete_products.Livro;
import ifba.inf011.s21_2.interfaces.Builder;

// Q2
// Director
public class CursoDirector<T> {

    private Builder<T> builder;

    public CursoDirector(Builder<T> builder) {
        this.builder = builder;
    }

    public void changeBuilder(Builder<T> builder) { 
        this.builder = builder;
    }

    public T construirCurso(String codigo,
                            String nome,
                            Disciplina[] disciplinas,
                            Livro[] livros) {
        this.builder.reset();
        this.builder.setCodigo(codigo);
        this.builder.setNome(nome);

        for(Disciplina disciplina : disciplinas) {
            this.builder.addDisciplina(disciplina);
        }

        for (Livro livro : livros) {
            this.builder.addLivro(livro);
        }

        return this.builder.getResult();
    }
}