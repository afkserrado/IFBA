package ifba.inf011.state.returnstate;

// Concrete State
public class PlayingPlaybackState implements PlaybackState {

    private static final PlayingPlaybackState INSTANCE = new PlayingPlaybackState();

    private PlayingPlaybackState() {
    }

    public static PlayingPlaybackState instance() {
        return INSTANCE;
    }

    @Override
    public PlaybackState lock(PlayerSession context) {
        context.pausePlayback();
        System.out.println("Player bloqueado");
        return LockedPlaybackState.instance();
    }

    @Override
    public PlaybackState play(PlayerSession context) {
        context.pausePlayback();
        return ReadyPlaybackState.instance();
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
        return "PLAYING";
    }
}