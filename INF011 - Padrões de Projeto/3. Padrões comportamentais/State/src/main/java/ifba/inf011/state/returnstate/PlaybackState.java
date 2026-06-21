package ifba.inf011.state.returnstate;

// State
public interface PlaybackState {
    PlaybackState lock(PlayerSession context);
    PlaybackState play(PlayerSession context);
    PlaybackState next(PlayerSession context);
    PlaybackState previous(PlayerSession context);
    String name();
}