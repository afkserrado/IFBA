package ifba.inf011.s24_2.concrete_factorys;

import java.time.LocalDate;

import ifba.inf011.s24_2.interfaces.Calendario;
import ifba.inf011.s24_2.interfaces.Evento;
import ifba.inf011.s24_2.interfaces.Fabrica;
import ifba.inf011.s24_2.products.CalendarioPessoal;
import ifba.inf011.s24_2.products.ConsultaMedica;

// Concrete Factory
public class FabricaPessoal implements Fabrica {
    
    @Override
    public Calendario criarCalendario(Short mes, Integer ano, Boolean not, String proprietario) {
        return new CalendarioPessoal(mes, ano, not, proprietario);
    }

    @Override 
    public Evento criarEvento(String descricao, LocalDate inicio, LocalDate fim, Integer prioridade, Boolean concluido, String geolocalizacao) {
        return new ConsultaMedica(descricao, inicio, fim, prioridade, concluido, geolocalizacao);
    }
}
