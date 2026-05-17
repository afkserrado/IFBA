package ifba.inf011.s25_1.products;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.s25_1.classes.TempoPassagem;

// Product
public class ListaPassagens {

    private List<TempoPassagem> passagens;

    public ListaPassagens() {
        this.passagens = new ArrayList<TempoPassagem>();
    }

    public List<TempoPassagem> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<TempoPassagem> passagens) {
        this.passagens = passagens;
    }

    public void addPassagem(TempoPassagem passagem) {
        this.passagens.add(passagem);
    }

    public void addPassagem(int numeroPrisma, double tempo) {
        this.passagens.add(new TempoPassagem(numeroPrisma, tempo));
    }

    public void registrarChegada(double tempo) {
        this.passagens.add(new TempoPassagem(999, tempo));
    }

    @Override
    public String toString() {
        return "ListaPassagens{" +
                "passagens=" + passagens +
                '}';
    }
}