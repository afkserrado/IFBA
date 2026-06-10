package ifba.inf011.p2.s24_1;

// Abstract Component

import java.time.LocalDateTime;

public abstract class AbstractComponent implements Component {
    
    protected String nome;
    private final LocalDateTime dataCriacao;
    protected Long tamanho;
    
    // Possibilita trabalhar com cache para o tamanho
    protected Component pai;

    public AbstractComponent(String nome) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public Component getPai() {
        return this.pai;
    }

    public void setPai(Component pai) {
        this.pai = pai;
    }
}
