package ifba.inf011.p2.s24_1;

import java.time.LocalDateTime;

import ifba.inf011.p2.s24_1.model.Credencial;

// Leaf do Composite
// Service do Proxy
public class Arquivo extends AbstractComponent {

    private String nome;
    private final LocalDateTime dataCriacao;
    private Long tamanho;

    public Arquivo(String nome, Long tamanho) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        this.tamanho = tamanho;
    }

    @Override
    public Long getTamanho() {
        return this.tamanho;
    }

    @Override
    public void ler(Credencial credencial) {
        System.out.println("Lendo o arquivo " + this.getNome() + "...");
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }
}
