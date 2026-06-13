package ifba.inf011.p2.s22_2;

public abstract class Obra {

    private String titulo;
    private String autor;
    private int ano;
    private double avaliacao;

    public Obra(String titulo, String autor, int ano, double avaliacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public int getAno() {
        return this.ano;
    }

    public double getAvaliacao() {
        return this.avaliacao;
    }
}