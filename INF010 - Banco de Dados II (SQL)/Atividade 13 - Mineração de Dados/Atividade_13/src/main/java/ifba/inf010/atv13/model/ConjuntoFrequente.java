package ifba.inf010.atv13.model;

import java.util.Set;

// Representa um conjunto frequente encontrado pelo algoritmo Apriori e seu suporte associado
public class ConjuntoFrequente {

    private Set<String> itens;
    private double suporte;

    public ConjuntoFrequente(Set<String> itens, double suporte) {
        this.itens = itens;
        this.suporte = suporte;
    }

    public Set<String> getItens() {
        return itens;
    }

    public double getSuporte() {
        return suporte;
    }

    @Override
    public String toString() {
        return itens + " -> " + String.format("%.2f%%", suporte * 100);
    }
}