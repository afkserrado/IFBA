package ifba.inf011.state;

import ifba.inf011.state.baseclass.AdvancedPlayer;
import ifba.inf011.state.classic.Player;
import ifba.inf011.state.contextparameter.PlayerController;
import ifba.inf011.state.returnstate.PlayerSession;

// Client
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Implementação 1: State clássico com referência ao contexto ===");

        Player player = new Player();

        System.out.println("Estado: " + player.getStateName());
        System.out.println(player.clickPlay());
        System.out.println("Estado: " + player.getStateName());
        System.out.println(player.clickNext());
        System.out.println(player.clickPlay());
        System.out.println("Estado: " + player.getStateName());
        System.out.println(player.clickLock());
        System.out.println("Estado: " + player.getStateName());
        System.out.println(player.clickNext());

        System.out.println("\n=== Implementação 2: State recebendo contexto como parâmetro ===");

        PlayerController controller = new PlayerController();

        System.out.println("Estado: " + controller.getStateName());
        System.out.println(controller.next());
        System.out.println(controller.play());
        System.out.println("Estado: " + controller.getStateName());
        System.out.println(controller.lock());
        System.out.println("Estado: " + controller.getStateName());
        System.out.println(controller.play());
        System.out.println(controller.lock());
        System.out.println("Estado: " + controller.getStateName());

        System.out.println("\n=== Implementação 3: State retornando próximo estado e usando Singletons ===");

        PlayerSession session = new PlayerSession();

        System.out.println("Estado: " + session.getStateName());
        session.play();
        System.out.println("Estado: " + session.getStateName());
        session.next();
        session.previous();
        session.lock();
        System.out.println("Estado: " + session.getStateName());
        session.next();
        session.lock();
        System.out.println("Estado: " + session.getStateName());

        System.out.println("\n=== Implementação 4: State com classe abstrata e comportamento padrão ===");

        AdvancedPlayer advancedPlayer = new AdvancedPlayer();

        System.out.println("Estado: " + advancedPlayer.getStateName());
        advancedPlayer.previous();
        advancedPlayer.play();
        System.out.println("Estado: " + advancedPlayer.getStateName());
        advancedPlayer.next();
        advancedPlayer.lock();
        System.out.println("Estado: " + advancedPlayer.getStateName());
        advancedPlayer.next();
        advancedPlayer.lock();
        System.out.println("Estado: " + advancedPlayer.getStateName());
    }
}