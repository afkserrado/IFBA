package ifba.inf011.p2.s26_1.q2.composite;
// Concrete Component do Decorator
public class ClipSimples implements ClipComponent {

    private String arquivo;
    private int duracao;

    public ClipSimples(String arquivo, int duracao) {
        this.arquivo = arquivo;
        this.duracao = duracao;
    }

    public String getArquivo() {
        return this.arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        if (duracao < 0) {
            throw new IllegalArgumentException("A duração não pode ser negativa.");
        }

        this.duracao = duracao;
    }

    @Override
    public String render() {
        return "[ClipSimples] " + this.arquivo + " (" + this.duracao + "s)";
    }

    @Override
    public String toString() {
        return this.render();
    }
}