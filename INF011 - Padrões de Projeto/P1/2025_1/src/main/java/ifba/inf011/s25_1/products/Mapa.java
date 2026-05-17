package ifba.inf011.s25_1.products;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.s25_1.enums.Dificuldade;
import ifba.inf011.s25_1.enums.Idade;
import ifba.inf011.s25_1.enums.Sexo;

import ifba.inf011.s25_1.classes.*;

// Product
public class Mapa {

    private Sexo sexo;
    private Idade idade;
    private Dificuldade dificuldade;

    private double ecn;
    private double escala;

    private PontoGeografico largada;
    private PontoGeografico partida;
    private PontoGeografico chegada;

    private List<Prisma> prismas;

    public Mapa() {
        this.prismas = new ArrayList<Prisma>();
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

    public double getEcn() {
        return ecn;
    }

    public void setEcn(double ecn) {
        this.ecn = ecn;
    }

    public double getEscala() {
        return escala;
    }

    public void setEscala(double escala) {
        this.escala = escala;
    }

    public PontoGeografico getLargada() {
        return largada;
    }

    public void setLargada(PontoGeografico largada) {
        this.largada = largada;
    }

    public PontoGeografico getPartida() {
        return partida;
    }

    public void setPartida(PontoGeografico partida) {
        this.partida = partida;
    }

    public PontoGeografico getChegada() {
        return chegada;
    }

    public void setChegada(PontoGeografico chegada) {
        this.chegada = chegada;
    }

    public List<Prisma> getPrismas() {
        return prismas;
    }

    public void setPrismas(List<Prisma> prismas) {
        this.prismas = prismas;
    }

    public void addPrisma(Prisma prisma) {
        this.prismas.add(prisma);
    }

    @Override
    public String toString() {
        return "Mapa{" +
                "sexo=" + sexo +
                ", idade=" + idade +
                ", dificuldade=" + dificuldade +
                ", ecn=" + ecn +
                ", escala=1:" + escala +
                ", largada=" + largada +
                ", partida=" + partida +
                ", chegada=" + chegada +
                ", prismas=" + prismas +
                '}';
    }
}