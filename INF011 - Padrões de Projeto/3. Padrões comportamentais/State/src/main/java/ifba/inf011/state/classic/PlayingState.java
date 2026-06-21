package ifba.inf011.state.classic;

// Concrete State
public class PlayingState extends PlayerState {

    public PlayingState(Player player) {
        super(player);
    }

    @Override
    public String onLock() {
        this.player.setCurrentTrackAfterStop();
        this.player.changeState(new LockedState(this.player));
        return "Reprodução parada e player bloqueado";
    }

    @Override
    public String onPlay() {
        String result = this.player.stopPlayback();
        this.player.changeState(new ReadyState(this.player));
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
        return "PLAYING";
    }
}