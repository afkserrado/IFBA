package ifba.inf011.p2.s24_2.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class AbstractEvento implements Evento {

    private String descricao;
    private Timestamp inicio = Timestamp.valueOf(LocalDateTime.now());
    private Timestamp fim = Timestamp.valueOf(LocalDateTime.now().plusHours(2));
    private Integer prioridade = 5;
    private Boolean concluido = false;
    private Geolocalizacao localizacao = Geolocalizacao.here();

    public AbstractEvento() {
        this.descricao = "";
    }

    public AbstractEvento(String descricao, Timestamp inicio, Timestamp fim, Integer prioridade, Boolean concluido, Geolocalizacao localizacao) {
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.prioridade = prioridade;
        this.concluido = concluido;
        this.localizacao = localizacao;
    }

    @Override
    public Boolean sobreposto(Evento evento) {
        if (evento == null) {
            return false;
        }
        return this.getInicio().before(evento.getTermino()) &&
               this.getTermino().after(evento.getInicio());
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public Timestamp getInicio() {
        return this.inicio;
    }

    @Override
    public Timestamp getTermino() {
        return this.fim;
    }

    @Override
    public Integer getPrioridade() {
        return this.prioridade;
    }

    public Boolean getConcluido() {
        return this.concluido;
    }

    @Override
    public Geolocalizacao getLocalizacao() {
        return this.localizacao;
    }

    @Override
    public Boolean iniciaEm(LocalDate dia) {
        if (dia == null) {
            return false;
        }
        return this.getInicio().toLocalDateTime().toLocalDate().equals(dia);
    }

    @Override
    public Boolean iniciaEntre(Timestamp inicio, Timestamp fim) {
        if (inicio == null || fim == null) {
            return false;
        }
        return !this.getInicio().before(inicio) && !this.getInicio().after(fim);
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setInicio(Timestamp inicio) {
        this.inicio = inicio;
    }

    public void setFim(Timestamp fim) {
        this.fim = fim;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public void setLocalizacao(Geolocalizacao localizacao) {
        this.localizacao = localizacao;
    }
}