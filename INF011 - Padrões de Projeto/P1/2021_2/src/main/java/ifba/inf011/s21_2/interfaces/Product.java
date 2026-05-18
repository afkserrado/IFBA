package ifba.inf011.s21_2.interfaces;

// Abstract product
public abstract class Product {
    
    protected String codigo;
    protected String nome;

    public Product(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "codigo=" + codigo + ", nome=" + nome;
    }
}