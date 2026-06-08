package ifba.inf011.p2.s24_2.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;

// Component do Decorator
public interface Calendario {
    void adicionarEventos(Evento evento);
    Collection<Evento> from(Timestamp inicio, Timestamp fim);
    Collection<Evento> day(LocalDate dia);
    Collection<Evento> today();
}