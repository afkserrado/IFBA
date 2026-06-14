package ifba.inf011.p2.s22_1.model;

public class Halteres extends Equipamento {

    private double peso;

    public Halteres(String identificador, int quantidade, double peso) {
        super(identificador, quantidade);
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Halteres[peso=" + peso + ", identificador=" + getIdentificador() +
               ", quantidade=" + getQuantidade() + "]";
    }
}