package ifba.inf011.p2.s22_2;

public class Album extends Obra {

    private String gravadora;
    private String estudio;
    private String autor;
    private int duracao;

    public Album(
            String titulo,
            int ano,
            double avaliacao,
            String gravadora,
            String estudio,
            String autor,
            int duracao) {

        super(titulo, ano, avaliacao);
        this.gravadora = gravadora;
        this.estudio = estudio;
        this.autor = autor;
        this.duracao = duracao;
    }

    public String getGravadora() {
        return gravadora;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getAutor() {
        return autor;
    }

    public int getDuracao() {
        return duracao;
    }
}