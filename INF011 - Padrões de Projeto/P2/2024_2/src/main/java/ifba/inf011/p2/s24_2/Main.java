package ifba.inf011.p2.s24_2;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import ifba.inf011.p2.s24_2.adapter.AulaAdapter;
import ifba.inf011.p2.s24_2.decorator.CalendarioOnlineDecorator;
import ifba.inf011.p2.s24_2.model.AbstractCalendario;
import ifba.inf011.p2.s24_2.model.AbstractEvento;
import ifba.inf011.p2.s24_2.model.Aula;
import ifba.inf011.p2.s24_2.model.Calendario;
import ifba.inf011.p2.s24_2.model.Evento;
import ifba.inf011.p2.s24_2.model.Geolocalizacao;
import ifba.inf011.p2.s24_2.services.CalendarioOnline;

public class Main {

    public static void main(String[] args) {
        q1();
        System.out.println("\n---------------------------------\n");
        q2();
    }

    private static void q1() {
        Calendario calendarioBase = new MeuCalendario(6, 2026, "anderson@ifba.edu.br");

        Evento prova = new MeuEvento(
                "Prova de Padrões de Projeto",
                Timestamp.valueOf(LocalDateTime.of(2026, 6, 10, 19, 0)),
                Timestamp.valueOf(LocalDateTime.of(2026, 6, 10, 21, 0)),
                1,
                false,
                new Geolocalizacao("IFBA", -12.9381, -38.5108)
        );

        calendarioBase.adicionarEventos(prova);

        CalendarioOnline online = new CalendarioOnline("https://api.exemplo.com/feriados/municipais");
        Calendario calendarioDecorado = new CalendarioOnlineDecorator(calendarioBase, online);

        Timestamp inicio = Timestamp.valueOf(LocalDateTime.of(2026, 6, 1, 0, 0));
        Timestamp fim = Timestamp.valueOf(LocalDateTime.of(2026, 6, 30, 23, 59));

        Collection<Evento> eventosPeriodo = calendarioDecorado.from(inicio, fim);
        Collection<Evento> eventosDia = calendarioDecorado.day(LocalDate.of(2026, 6, 10));
        Collection<Evento> eventosHoje = calendarioDecorado.today();

        System.out.println("=== QUESTÃO 1: DECORATOR ===");

        System.out.println("\n=== EVENTOS NO PERÍODO ===");
        imprimir(eventosPeriodo);

        System.out.println("\n=== EVENTOS NO DIA 10/06/2026 ===");
        imprimir(eventosDia);

        System.out.println("\n=== EVENTOS DE HOJE ===");
        imprimir(eventosHoje);
    }

    private static void q2() {
        Calendario calendario = new MeuCalendario(6, 2026, "anderson@ifba.edu.br");

        Aula aula = new Aula(
                "INF011",
                "Padrões de Projeto",
                "Anderson",
                new Geolocalizacao("Sala 201", -12.9381, -38.5108),
                LocalDateTime.of(2026, 6, 12, 19, 0),
                LocalDateTime.of(2026, 6, 12, 20, 40)
        );

        Evento aulaAdaptada = new AulaAdapter(aula);
        calendario.adicionarEventos(aulaAdaptada);

        Collection<Evento> eventosDia = calendario.day(LocalDate.of(2026, 6, 12));
        Collection<Evento> eventosHoje = calendario.today();

        System.out.println("=== QUESTÃO 2: ADAPTER ===");

        System.out.println("\n=== EVENTOS NO DIA 12/06/2026 ===");
        imprimir(eventosDia);

        System.out.println("\n=== EVENTOS DE HOJE ===");
        imprimir(eventosHoje);
    }

    private static void imprimir(Collection<Evento> eventos) {
        for (Evento evento : eventos) {
            System.out.println(
                evento.getDescricao() + " | " +
                evento.getInicio() + " | " +
                evento.getTermino() + " | " +
                evento.getLocalizacao()
            );
        }
    }

    // Classes de suporte, já que não criei calendários e eventos concretos
    private static class MeuCalendario extends AbstractCalendario {
        public MeuCalendario(Integer mes, Integer ano, String email) {
            super(mes, ano, email);
        }
    }

    private static class MeuEvento extends AbstractEvento {
        public MeuEvento(String descricao, Timestamp inicio, Timestamp fim, Integer prioridade,
                         Boolean concluido, Geolocalizacao localizacao) {
            super(descricao, inicio, fim, prioridade, concluido, localizacao);
        }
    }
}