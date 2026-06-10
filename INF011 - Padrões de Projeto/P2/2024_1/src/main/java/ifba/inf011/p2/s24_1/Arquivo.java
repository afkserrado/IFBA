package ifba.inf011.p2.s24_1;

import ifba.inf011.p2.s24_1.model.Credencial;

// Leaf do Composite
// Service do Proxy
public class Arquivo extends AbstractComponent {

    public Arquivo(String nome, Long tamanho) {
        super(nome);
        this.tamanho = tamanho;
    }

    @Override
    public Long getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public void ler(Credencial credencial) {
        System.out.println("Lendo o arquivo " + this.getNome() + "...");
    }
}
