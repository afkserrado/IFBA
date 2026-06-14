package ifba.inf011.p2.s22_1.model;

public abstract class Equipamento {

    private String identificador;
    private int quantidade;

    public Equipamento(String identificador, int quantidade) {
        this.identificador = identificador;
        this.quantidade = quantidade;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[identificador=" + identificador + ", quantidade=" + quantidade + "]";
    }
}