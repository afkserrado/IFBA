package ifba.inf011.p2.s22_1.model;

public class Acessorios extends Equipamento {

    private String descricao;

    public Acessorios(String identificador, int quantidade, String descricao) {
        super(identificador, quantidade);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Acessorios[descricao=" + descricao + ", identificador=" + getIdentificador() +
               ", quantidade=" + getQuantidade() + "]";
    }
}