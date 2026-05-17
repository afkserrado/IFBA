package ifba.inf011.s24_1.interfaces.products;

import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {
    
    private double ataqueRapido = 10;
    private Boolean habilidade = false;
    private List<Arma> armas;
    // demais atributos...

    // Construtor
    public Personagem() {
        armas = new ArrayList<>();
    }

    // Métodos para a construção do objeto via builder
    public void setAtaqueRapido(double ataque) {
        this.ataqueRapido = ataque;
    }

    public void setHabilidade(Boolean hab) {
        this.habilidade = hab;
    }

    public void addArma(Arma arma) {
        armas.add(arma);
    }
    // demais métodos construtivos (setters)...

    // Métodos da interface
    public double getAtaqueRapido() {
        return ataqueRapido;
    }
    // demais getters da interface...
}
