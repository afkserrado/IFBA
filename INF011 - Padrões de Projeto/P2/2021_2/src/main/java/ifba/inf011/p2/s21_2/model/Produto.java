package ifba.inf011.p2.s21_2.model;

public abstract class Produto implements Precificavel {

    private String codigo;
    private String nome;

    public Produto(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}