package ifba.inf011.p2.s22_1.model;

public class Serie {

    private int numRepeticoes;
    private int qtde;
    private Exercicio exercicio;

    public Serie(Exercicio exercicio, int numRepeticoes, int qtde) {
        this.exercicio = exercicio;
        this.numRepeticoes = numRepeticoes;
        this.qtde = qtde;
    }

    public int getNumRepeticoes() {
        return numRepeticoes;
    }

    public void setNumRepeticoes(int numRepeticoes) {
        this.numRepeticoes = numRepeticoes;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    @Override
    public String toString() {
        return "Serie[numRepeticoes=" + numRepeticoes + ", qtde=" + qtde +
               ", exercicio=" + exercicio + "]";
    }
}