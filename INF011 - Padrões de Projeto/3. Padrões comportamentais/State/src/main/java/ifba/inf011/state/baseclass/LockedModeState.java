package ifba.inf011.state.baseclass;

// Concrete State
public class LockedModeState extends AbstractModeState {

    @Override
    public ModeState lock(AdvancedPlayer context) {
        System.out.println("Player desbloqueado");
        return new ReadyModeState();
    }

    @Override
    public String name() {
        return "LOCKED";
    }
}