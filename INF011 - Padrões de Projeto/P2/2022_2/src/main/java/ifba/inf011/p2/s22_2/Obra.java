package ifba.inf011.p2.s22_2;

public abstract class Obra {

    private String titulo;
    private int ano;
    private double avaliacao;

    public Obra(String titulo, int ano, double avaliacao) {
        this.titulo = titulo;
        this.ano = ano;
        this.avaliacao = avaliacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public double getAvaliacao() {
        return avaliacao;
    }
}