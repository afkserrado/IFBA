package ifba.inf011.s24_2.products;

import java.time.LocalDate;

import ifba.inf011.s24_2.interfaces.Evento;

// Product
public class Partida extends Evento {
    
    private String equipeA;
    private String equipeB;
    private Integer pontuacaoA;
    private Integer pontuacaoB;

    // Exclusivamente criado para instanciação via builder
    public Partida() {}

    // Construtor padrão para o Concrete Factory correspondente
    public Partida(String descricao, LocalDate inicio, LocalDate fim, Integer prioridade, Boolean concluido, String geolocalizacao, String equipeA, String equipeB, Integer pontuacaoA, Integer pontuacaoB) {
        super(descricao, inicio, fim, prioridade, concluido, geolocalizacao);

        this.equipeA = equipeA;
        this.equipeB = equipeB;
        this.pontuacaoA = pontuacaoA;
        this.pontuacaoB = pontuacaoB;
    }

    public void setEquipeA(String equipeA) {
        this.equipeA = equipeA;
    }

    public void setEquipeB(String equipeB) {
        this.equipeB = equipeB;
    }
}
