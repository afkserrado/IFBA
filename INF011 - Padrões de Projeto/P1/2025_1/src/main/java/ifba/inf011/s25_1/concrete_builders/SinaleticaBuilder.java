package ifba.inf011.s25_1.concrete_builders;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.s25_1.classes.PontoGeografico;
import ifba.inf011.s25_1.classes.Prisma;
import ifba.inf011.s25_1.enums.Dificuldade;
import ifba.inf011.s25_1.enums.Idade;
import ifba.inf011.s25_1.enums.Sexo;
import ifba.inf011.s25_1.interfaces.BuilderPercurso;
import ifba.inf011.s25_1.products.Sinaletica;

public class SinaleticaBuilder implements BuilderPercurso {

    private Sinaletica sinaletica;
    private PontoGeografico partida;
    private List<Prisma> prismas;
    private PontoGeografico chegada;

    public SinaleticaBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.sinaletica = new Sinaletica();
        this.partida = null;
        this.prismas = new ArrayList<>();
        this.chegada = null;
    }

    @Override
    public void setCategoria(Sexo sexo, Idade idade, Dificuldade dificuldade) {
        sinaletica.setSexo(sexo);
        sinaletica.setIdade(idade);
        sinaletica.setDificuldade(dificuldade);
    }

    @Override
    public void setECN(double ecn) {
        // Sinaletica não usa ECN diretamente
    }

    @Override
    public void setEscala(double escala) {
        // Sinaletica não usa escala diretamente
    }

    @Override
    public void setLargada(double lat, double lon, double alt) {
        // Sinaletica não usa largada diretamente
    }

    @Override
    public void setPartida(double lat, double lon, double alt) {
        this.partida = new PontoGeografico(lat, lon, alt);
    }

    @Override
    public void addPrisma(int numero, double lat, double lon, double alt) {
        this.prismas.add(new Prisma(numero, new PontoGeografico(lat, lon, alt)));
    }

    @Override
    public void setChegada(double lat, double lon, double alt) {
        this.chegada = new PontoGeografico(lat, lon, alt);
    }

    public Sinaletica getResultado() {
        List<PontoGeografico> pontos = new ArrayList<>();

        if (partida != null) {
            pontos.add(partida);
        }

        for (Prisma prisma : prismas) {
            pontos.add(prisma.getLocalizacao());
        }

        if (chegada != null) {
            pontos.add(chegada);
        }

        double distanciaTotal = 0.0;
        double maiorDesnivel = 0.0;

        for (int i = 0; i < pontos.size() - 1; i++) {
            PontoGeografico atual = pontos.get(i);
            PontoGeografico proximo = pontos.get(i + 1);

            distanciaTotal += calcularDistancia(atual, proximo);

            double desnivel = Math.abs(proximo.getAltitude() - atual.getAltitude());
            if (desnivel > maiorDesnivel) {
                maiorDesnivel = desnivel;
            }
        }

        sinaletica.setDistancia(distanciaTotal);
        sinaletica.setMaiorDesnivel(maiorDesnivel);

        return this.sinaletica;
    }

    private double calcularDistancia(PontoGeografico p1, PontoGeografico p2) {
        double dLat = p2.getLatitude() - p1.getLatitude();
        double dLon = p2.getLongitude() - p1.getLongitude();
        double dAlt = p2.getAltitude() - p1.getAltitude();

        return Math.sqrt(dLat * dLat + dLon * dLon + dAlt * dAlt);
    }
}