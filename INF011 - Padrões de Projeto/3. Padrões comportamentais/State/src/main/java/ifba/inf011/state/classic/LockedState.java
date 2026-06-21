package ifba.inf011.state.classic;

// Concrete State
public class LockedState extends PlayerState {

    public LockedState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        this.player.changeState(new ReadyState(this.player));
        return "Player desbloqueado";
    }

    @Override
    public String onPlay() {
        return "Player bloqueado. Desbloqueie antes de tocar.";
    }

    @Override
    public String onNext() {
        return "Player bloqueado. Não é possível avançar.";
    }

    @Override
    public String onPrevious() {
        return "Player bloqueado. Não é possível voltar.";
    }

    @Override
    public String name() {
        return "LOCKED";
    }
}