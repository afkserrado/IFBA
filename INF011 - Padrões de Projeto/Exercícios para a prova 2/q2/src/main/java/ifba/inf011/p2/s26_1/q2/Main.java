package ifba.inf011.p2.s26_1.q2;

import ifba.inf011.p2.s26_1.q2.composite.ClipComponent;
import ifba.inf011.p2.s26_1.q2.composite.ClipComposto;
import ifba.inf011.p2.s26_1.q2.composite.ClipSimples;
import ifba.inf011.p2.s26_1.q2.decorator.FiltroCorClip;
import ifba.inf011.p2.s26_1.q2.decorator.LegendaEmbutidaClip;

public class Main {

    public static void main(String[] args) {

        ClipComponent cenaPrincipal = new ClipComposto("Cena Principal");

        ClipComponent abertura = new ClipSimples("abertura.mp4", 30);
        ClipComponent dialogo = new ClipSimples("dialogo.mp4", 90);

        ClipComponent flashback = new ClipComposto("Flashback");
        flashback.adicionar(new ClipSimples("flashback_01.mp4", 20));
        flashback.adicionar(new ClipSimples("flashback_02.mp4", 25));

        cenaPrincipal.adicionar(abertura);
        cenaPrincipal.adicionar(dialogo);
        cenaPrincipal.adicionar(flashback);

        System.out.println("===== Composite =====");
        System.out.println(cenaPrincipal.render());
        System.out.println("Duração total: " + cenaPrincipal.getDuracao() + "s");

        System.out.println("\n===== Decorator em ClipSimples =====");

        ClipComponent clipDecorado =
                new LegendaEmbutidaClip(
                    new FiltroCorClip(
                        new ClipSimples("take_close_final.mp4", 15),
                        "Rec709"
                    ),
                    "Legenda embutida em português"
                );

        System.out.println(clipDecorado.render());
        System.out.println("Duração: " + clipDecorado.getDuracao() + "s");

        System.out.println("\n===== Decorator em ClipComposto =====");

        ClipComponent cenaComFiltro =
                new FiltroCorClip(
                    cenaPrincipal,
                    "Cinema quente"
                );

        System.out.println(cenaComFiltro.render());
        System.out.println("Duração: " + cenaComFiltro.getDuracao() + "s");
    }
}