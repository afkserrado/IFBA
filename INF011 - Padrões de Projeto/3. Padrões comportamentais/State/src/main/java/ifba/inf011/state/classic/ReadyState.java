package ifba.inf011.state.classic;

// Concrete State
public class ReadyState extends PlayerState {

    public ReadyState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        this.player.changeState(new LockedState(this.player));
        return "Player bloqueado";
    }

    @Override
    public String onPlay() {
        String result = this.player.startPlayback();
        this.player.changeState(new PlayingState(this.player));
        return result;
    }

    @Override
    public String onNext() {
        return this.player.nextTrack();
    }

    @Override
    public String onPrevious() {
        return this.player.previousTrack();
    }

    @Override
    public String name() {
        return "READY";
    }
}