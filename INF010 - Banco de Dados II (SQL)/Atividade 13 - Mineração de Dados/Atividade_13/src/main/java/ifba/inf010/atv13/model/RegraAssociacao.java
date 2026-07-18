package ifba.inf010.atv13.model;

import java.util.Set;

// Representa as regras de associação entre um antecedente e um consequente
public class RegraAssociacao {

    private Set<String> antecedente;
    private Set<String> consequente;
    private double suporte;
    private double confianca;

    public RegraAssociacao(Set<String> antecedente, Set<String> consequente, double suporte, double confianca) {
        this.antecedente = antecedente;
        this.consequente = consequente;
        this.suporte = suporte;
        this.confianca = confianca;
    }

    public Set<String> getAntecedente() {
        return antecedente;
    }

    public Set<String> getConsequente() {
        return consequente;
    }

    public double getSuporte() {
        return suporte;
    }

    public double getConfianca() {
        return confianca;
    }

    @Override
    public String toString() {

        return antecedente +
                " => " +
                consequente +
                String.format(
                        " (sup: %.2f%%, conf: %.2f%%)",
                        suporte * 100,
                        confianca * 100
                );
    }
}