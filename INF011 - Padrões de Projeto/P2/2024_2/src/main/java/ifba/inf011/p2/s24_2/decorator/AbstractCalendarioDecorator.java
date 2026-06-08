package ifba.inf011.p2.s24_2.decorator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ifba.inf011.p2.s24_2.model.Calendario;
import ifba.inf011.p2.s24_2.model.Evento;
import ifba.inf011.p2.s24_2.services.CalendarioOnline;

// Base Decorator do Decorator
public abstract class AbstractCalendarioDecorator implements Calendario {
	
	protected Calendario inner;
	
	public AbstractCalendarioDecorator(Calendario inner) {
        this.inner = inner;
    }
	
	@Override
    public void adicionarEventos(Evento evento) {
        inner.adicionarEventos(evento);
    }

    @Override
    public Collection<Evento> from(Timestamp inicio, Timestamp fim) {
        return inner.from(inicio, fim);
    }

    @Override
    public Collection<Evento> day(LocalDate dia) {
        return inner.day(dia);
    }

    @Override
    public Collection<Evento> today() {
        return inner.today();
    }
}
