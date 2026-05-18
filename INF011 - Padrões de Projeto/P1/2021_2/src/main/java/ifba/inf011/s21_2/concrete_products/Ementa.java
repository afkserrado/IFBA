package ifba.inf011.s21_2.concrete_products;

import java.util.ArrayList;
import java.util.List;

// Concrete product
public class Ementa {

    private String codigoCurso;
    private String nomeCurso;
    private List<Disciplina> disciplinas;
    private List<Livro> livros;

    public Ementa() {
        this.disciplinas = new ArrayList<>();
        this.livros = new ArrayList<>();
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
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

    public int getCargaHorariaTotal() {
        int total = 0;
        for (Disciplina disciplina : disciplinas) {
            total += disciplina.getCargaHoraria();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Ementa{" +
                "codigoCurso='" + codigoCurso + '\'' +
                ", nomeCurso='" + nomeCurso + '\'' +
                ", disciplinas=" + disciplinas +
                ", livros=" + livros +
                ", cargaHorariaTotal=" + getCargaHorariaTotal() +
                '}';
    }
}