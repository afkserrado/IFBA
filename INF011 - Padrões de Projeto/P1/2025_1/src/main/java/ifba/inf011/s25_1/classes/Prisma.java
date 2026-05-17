package ifba.inf011.s25_1.classes;

public class Prisma {

    private int numero;
    private PontoGeografico localizacao;

    public Prisma(int numero, PontoGeografico localizacao) {
        this.numero = numero;
        this.localizacao = localizacao;
    }

    public int getNumero() {
        return numero;
    }

    public PontoGeografico getLocalizacao() {
        return localizacao;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLocalizacao(PontoGeografico localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Prisma " + numero + " em " + localizacao;
    }
}