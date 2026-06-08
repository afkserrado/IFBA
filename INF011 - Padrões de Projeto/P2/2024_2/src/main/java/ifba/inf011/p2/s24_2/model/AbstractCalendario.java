package ifba.inf011.p2.s24_2.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCalendario implements Calendario {

    private Integer mes;
    private Integer ano;
    private String email;
    private List<Evento> eventos;

    public AbstractCalendario() {
        this.eventos = new ArrayList<Evento>();
    }

    public AbstractCalendario(Integer mes, Integer ano, String email) {
        this.mes = mes;
        this.ano = ano;
        this.email = email;
        this.eventos = new ArrayList<Evento>();
    }

    @Override
    public void adicionarEventos(Evento evento) {
        if (evento != null) {
            this.eventos.add(evento);
        }
    }

    @Override
    public Collection<Evento> from(Timestamp inicio, Timestamp fim) {
        List<Evento> resultado = new ArrayList<Evento>();
        for (Evento evento : this.eventos) {
            if (evento.iniciaEntre(inicio, fim)) {
                resultado.add(evento);
            }
        }
        return resultado;
    }

    @Override
    public Collection<Evento> day(LocalDate dia) {
        List<Evento> resultado = new ArrayList<Evento>();
        for (Evento evento : this.eventos) {
            if (evento.iniciaEm(dia)) {
                resultado.add(evento);
            }
        }
        return resultado;
    }

    @Override
    public Collection<Evento> today() {
        return this.day(LocalDate.now());
    }

    public Boolean sobreposto(Evento evento) {
        if (evento == null) {
            return false;
        }

        for (Evento atual : this.eventos) {
            if (atual.sobreposto(evento)) {
                return true;
            }
        }
        return false;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected List<Evento> getEventos() {
        return eventos;
    }
}