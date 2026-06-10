package ifba.inf011.p2.s24_1;

import java.time.LocalDateTime;

// Abstract Component
public abstract class AbstractComponent implements Component {
    
    // Possibilita trabalhar com cache para o tamanho
    protected Component pai;

    public abstract String getNome();
    public abstract void setNome(String nome);
    public abstract LocalDateTime getDataCriacao();

    public Component getPai() {
        return this.pai;
    }

    public void setPai(Component pai) {
        this.pai = pai;
    }
}
