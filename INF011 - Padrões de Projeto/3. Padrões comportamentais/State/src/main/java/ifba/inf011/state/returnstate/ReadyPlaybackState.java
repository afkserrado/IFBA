package ifba.inf011.state.returnstate;

// Concrete State
public class ReadyPlaybackState implements PlaybackState {

    private static final ReadyPlaybackState INSTANCE = new ReadyPlaybackState();

    private ReadyPlaybackState() {
    }

    public static ReadyPlaybackState instance() {
        return INSTANCE;
    }

    @Override
    public PlaybackState lock(PlayerSession context) {
        System.out.println("Player bloqueado");
        return LockedPlaybackState.instance();
    }

    @Override
    public PlaybackState play(PlayerSession context) {
        context.startPlayback();
        return PlayingPlaybackState.instance();
    }

    @Override
    public PlaybackState next(PlayerSession context) {
        context.nextTrack();
        return this;
    }

    @Override
    public PlaybackState previous(PlayerSession context) {
        context.previousTrack();
        return this;
    }

    @Override
    public String name() {
        return "READY";
    }
}