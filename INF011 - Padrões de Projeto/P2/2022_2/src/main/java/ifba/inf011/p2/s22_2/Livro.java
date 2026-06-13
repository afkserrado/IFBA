package ifba.inf011.p2.s22_2;

public class Livro extends Obra {

    private String editora;
    private String idioma;
    private int numeroPaginas;

    public Livro(
            String titulo,
            int ano,
            double avaliacao,
            String editora,
            String idioma,
            String autor,
            int numeroPaginas) {

        super(titulo, autor, ano, avaliacao);
        this.editora = editora;
        this.idioma = idioma;
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditora() {
        return editora;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }
}