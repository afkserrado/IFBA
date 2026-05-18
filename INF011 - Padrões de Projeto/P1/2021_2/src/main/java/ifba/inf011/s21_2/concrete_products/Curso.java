package ifba.inf011.s21_2.concrete_products;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.s21_2.interfaces.Product;

// Concrete product
public class Curso extends Product {

    private final List<Disciplina> disciplinas;
    private final List<Livro> livros;

    // Construtor padrão
    public Curso(String codigo, String nome) {
        super(codigo, nome);
        this.disciplinas = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    // Construtor privado para o singleton da Q3
    private Curso(Curso prototipo) {
        super(prototipo.codigo, prototipo.nome);
        this.disciplinas = new ArrayList<>();
        this.livros = new ArrayList<>();

        for (Disciplina disciplina : prototipo.disciplinas) {
            Disciplina copiaDisciplina = new Disciplina(
                disciplina.getCodigo(),
                disciplina.getNome()
            );

            copiaDisciplina.setCargaHoraria(disciplina.getCargaHoraria());
            this.disciplinas.add(copiaDisciplina);
        }

        for (Livro livro : prototipo.livros) {
            Livro copiaLivro = new Livro(
                livro.getCodigo(),
                livro.getNome()
            );
            this.livros.add(copiaLivro);
        }
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

    // Q3
    @Override
    public Curso clone() {
        return new Curso(this);
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