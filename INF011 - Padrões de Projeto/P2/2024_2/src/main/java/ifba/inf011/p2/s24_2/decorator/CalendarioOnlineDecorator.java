package ifba.inf011.p2.s24_2.decorator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ifba.inf011.p2.s24_2.model.Calendario;
import ifba.inf011.p2.s24_2.model.Evento;
import ifba.inf011.p2.s24_2.services.CalendarioOnline;

// Concrete Decorator do Decorator
public class CalendarioOnlineDecorator extends AbstractCalendarioDecorator {

	private CalendarioOnline online;
	private Map<String, Collection<Evento>> cacheFrom = new HashMap<String, Collection<Evento>>();
	private Map<LocalDate, Collection<Evento>> cacheDay = new HashMap<LocalDate, Collection<Evento>>();
	
	public CalendarioOnlineDecorator(Calendario inner, CalendarioOnline online) {
        super(inner);
        this.online = online;
    }

    @Override
    public Collection<Evento> from(Timestamp inicio, Timestamp fim) {
        Collection<Evento> eventosOriginais = super.from(inicio, fim);
        Collection<Evento> eventosOnline = this.consultarComCache(inicio, fim);
        
        Collection<Evento> resultado = new ArrayList<Evento>();
        resultado.addAll(eventosOriginais);
        resultado.addAll(eventosOnline);
        
        return resultado;
    }

    @Override
    public Collection<Evento> day(LocalDate dia) {
    	Collection<Evento> eventosOriginais = super.day(dia);
        Collection<Evento> eventosOnline = this.consultarComCache(dia);
        
        Collection<Evento> resultado = new ArrayList<Evento>();
        resultado.addAll(eventosOriginais);
        resultado.addAll(eventosOnline);
        
        return resultado;
    }

    @Override
    public Collection<Evento> today() {
        return this.day(LocalDate.now());
    }
    
    protected Collection<Evento> consultarComCache(Timestamp inicio, Timestamp fim) {
        String chave = inicio.toString() + "|" + fim.toString();
        Collection<Evento> eventosOnline = cacheFrom.get(chave);

        if(eventosOnline == null) {
            eventosOnline = this.online.consultar(inicio, fim);
            cacheFrom.put(chave, eventosOnline);
        }

        return eventosOnline;
    }
    
    protected Collection<Evento> consultarComCache(LocalDate dia) {
        Collection<Evento> eventosOnline = cacheDay.get(dia);

        if(eventosOnline == null) {
            eventosOnline = this.online.consultar(dia);
            cacheDay.put(dia, eventosOnline);
        }

        return eventosOnline;
    }
}
