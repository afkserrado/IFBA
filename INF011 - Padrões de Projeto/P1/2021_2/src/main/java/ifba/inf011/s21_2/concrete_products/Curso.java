package ifba.inf011.s21_2.concrete_products;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.s21_2.interfaces.Product;

// Concrete product
public class Curso extends Product {

    private List<Disciplina> disciplinas;
    private List<Livro> livros;

    public Curso() {
        super(null, null);
        this.disciplinas = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void addDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void addLivro(Livro livro) {
        this.livros.add(livro);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", disciplinas=" + disciplinas +
                ", livros=" + livros +
                '}';
    }
}