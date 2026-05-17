package ifba.inf011.s25_1.classes;

public class TempoPassagem {

    private int numeroPrisma;
    private double tempo;

    public TempoPassagem(int numeroPrisma, double tempo) {
        this.numeroPrisma = numeroPrisma;
        this.tempo = tempo;
    }

    public int getNumeroPrisma() {
        return numeroPrisma;
    }

    public double getTempo() {
        return tempo;
    }

    public void setNumeroPrisma(int numeroPrisma) {
        this.numeroPrisma = numeroPrisma;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "(" + numeroPrisma + ", " + tempo + ")";
    }
}