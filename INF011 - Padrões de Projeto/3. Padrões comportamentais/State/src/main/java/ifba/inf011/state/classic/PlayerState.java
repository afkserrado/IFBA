package ifba.inf011.state.classic;

// State
public abstract class PlayerState {

    protected Player player;

    public PlayerState(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();
    public abstract String name();
}