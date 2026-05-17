package ifba.inf011.s24_2.interfaces;

import java.time.LocalDate;

// Abstract Product
public abstract class Evento {
    private String descricao;
    private LocalDate inicio = LocalDate.now();
    private LocalDate fim = inicio.plusDays(2);
    private Integer prioridade = 5;
    private Boolean concluido = false;
    private String geolocalizacao;

    public Evento() {}

    public Evento(String descricao, LocalDate inicio, LocalDate fim, Integer prioridade, Boolean concluido, String geolocalizacao) {

        this.descricao = descricao;

        if (inicio != null) {
            this.inicio = inicio;
        }

        if (fim != null) {
            this.fim = fim;
        }

        if (prioridade != null) {
            this.prioridade = prioridade;
        }

        if (concluido != null) {
            this.concluido = concluido;
        }

        this.geolocalizacao = geolocalizacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // ... Demais setters para os demais atributos
}
