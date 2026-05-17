package ifba.inf011.s24_2.interfaces;

import java.time.LocalDate;

// Abstract Factory
public interface Fabrica {
    Calendario criarCalendario(Short mes, Integer ano, Boolean not, String proprietario);
    Evento criarEvento(String descricao, LocalDate inicio, LocalDate fim, Integer prioridade, Boolean concluido, String geolocalizacao);
}
