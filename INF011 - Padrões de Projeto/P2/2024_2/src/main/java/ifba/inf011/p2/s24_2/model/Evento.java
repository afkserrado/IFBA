package ifba.inf011.p2.s24_2.model;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface Evento {
    Boolean sobreposto(Evento evento);
    String getDescricao();
    Timestamp getInicio();
    Timestamp getTermino();
    Integer getPrioridade();
    Geolocalizacao getLocalizacao();
    Boolean iniciaEm(LocalDate dia);
    Boolean iniciaEntre(Timestamp inicio, Timestamp fim);
}