package ifba.inf011.s25_1.products;

import ifba.inf011.s25_1.enums.Dificuldade;
import ifba.inf011.s25_1.enums.Idade;
import ifba.inf011.s25_1.enums.Sexo;

// Product
public class Sinaletica {

    private Sexo sexo;
    private Idade idade;
    private Dificuldade dificuldade;

    private double distancia; // Maior distância
    private double desnivel; // Maior desnível

    public Sinaletica() {
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Idade getIdade() {
        return idade;
    }

    public void setIdade(Idade idade) {
        this.idade = idade;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getDesnivel() {
        return desnivel;
    }

    public void setDesnivel(double desnivel) {
        this.desnivel = desnivel;
    }

    @Override
    public String toString() {
        return "Sinaletica{" +
                "sexo=" + sexo +
                ", idade=" + idade +
                ", dificuldade=" + dificuldade +
                ", distancia=" + distancia +
                ", desnivel=" + desnivel +
                '}';
    }
}