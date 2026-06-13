package ifba.inf011.p2.s22_2;

public class Livro extends Obra {

    private String editora;
    private String idioma;
    private String autor;
    private int numeroPaginas;

    public Livro(
            String titulo,
            int ano,
            double avaliacao,
            String editora,
            String idioma,
            String autor,
            int numeroPaginas) {

        super(titulo, ano, avaliacao);
        this.editora = editora;
        this.idioma = idioma;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditora() {
        return editora;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }
}