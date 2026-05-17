package ifba.inf011.s24_2.products;

import java.time.LocalDate;

import ifba.inf011.s24_2.interfaces.Evento;

// Product
public class ConsultaMedica extends Evento {
    
    public ConsultaMedica(String descricao, LocalDate inicio, LocalDate fim, Integer prioridade, Boolean concluido, String geolocalizacao) {
        super(descricao, inicio, fim, prioridade, concluido, geolocalizacao);
    }
}
