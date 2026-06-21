package ifba.inf011.state.contextparameter;

// Concrete State
public class LockedControlState implements ControlState {

    @Override
    public String pressLock(PlayerController context) {
        context.setState(new ReadyControlState());
        return "Player desbloqueado";
    }

    @Override
    public String pressPlay(PlayerController context) {
        return "Comando play ignorado. Player bloqueado.";
    }

    @Override
    public String pressNext(PlayerController context) {
        return "Comando next ignorado. Player bloqueado.";
    }

    @Override
    public String pressPrevious(PlayerController context) {
        return "Comando previous ignorado. Player bloqueado.";
    }

    @Override
    public String name() {
        return "LOCKED";
    }
}