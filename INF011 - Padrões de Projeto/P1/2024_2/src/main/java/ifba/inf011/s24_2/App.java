package ifba.inf011.s24_2;

import java.time.LocalDate;

import ifba.inf011.s24_2.concrete_builders.PartidaBuilder;
import ifba.inf011.s24_2.concrete_factorys.FabricaPessoal;
import ifba.inf011.s24_2.directors.PartidaDirector;
import ifba.inf011.s24_2.interfaces.Calendario;
import ifba.inf011.s24_2.interfaces.Evento;
import ifba.inf011.s24_2.interfaces.Fabrica;
import ifba.inf011.s24_2.products.Partida;

public class App {
        
    public static void main(String[] args) {
        Fabrica fabrica = new FabricaPessoal();

        Calendario calendario = fabrica.criarCalendario((short) 5, 2026, true, "Anderson");
        Evento evento = fabrica.criarEvento(
                "Consulta com cardiologista",
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 5, 20),
                3,
                false,
                "Salvador"
        );

        calendario.download("calendar.ifba.edu.br");
        calendario.sinalizarEventos(LocalDate.of(2026, 5, 1));

        PartidaDirector partidaDirector = new PartidaDirector(new PartidaBuilder());
        Partida partida = partidaDirector.criarPartida("Final da Copa do Mundo de 1970", "Brasil", "Itália");        
    }
}