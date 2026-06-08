package ifba.inf011.p2.s24_2.services;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import ifba.inf011.p2.s24_2.model.Evento;

public class CalendarioOnline {

    private String uri;

    public CalendarioOnline(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return this.uri;
    }

    public Collection<Evento> consultar(Timestamp inicio, Timestamp fim) {
        return new ArrayList<Evento>();
    }

    public Collection<Evento> consultar(LocalDate dia) {
        return new ArrayList<Evento>();
    }
}