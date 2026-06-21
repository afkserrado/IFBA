package ifba.inf011.state.returnstate;

// Concrete State
public class LockedPlaybackState implements PlaybackState {

    private static final LockedPlaybackState INSTANCE = new LockedPlaybackState();

    private LockedPlaybackState() {
    }

    public static LockedPlaybackState instance() {
        return INSTANCE;
    }

    @Override
    public PlaybackState lock(PlayerSession context) {
        System.out.println("Player desbloqueado");
        return ReadyPlaybackState.instance();
    }

    @Override
    public PlaybackState play(PlayerSession context) {
        System.out.println("Play ignorado. Player bloqueado.");
        return this;
    }

    @Override
    public PlaybackState next(PlayerSession context) {
        System.out.println("Next ignorado. Player bloqueado.");
        return this;
    }

    @Override
    public PlaybackState previous(PlayerSession context) {
        System.out.println("Previous ignorado. Player bloqueado.");
        return this;
    }

    @Override
    public String name() {
        return "LOCKED";
    }
}