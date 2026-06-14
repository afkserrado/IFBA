package ifba.inf011.p2.s22_1.model;

public class Maquinas extends Equipamento {

    private String descricao;
    private String marca;

    public Maquinas(String identificador, int quantidade, String descricao, String marca) {
        super(identificador, quantidade);
        this.descricao = descricao;
        this.marca = marca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Maquinas[descricao=" + descricao + ", marca=" + marca +
               ", identificador=" + getIdentificador() +
               ", quantidade=" + getQuantidade() + "]";
    }
}