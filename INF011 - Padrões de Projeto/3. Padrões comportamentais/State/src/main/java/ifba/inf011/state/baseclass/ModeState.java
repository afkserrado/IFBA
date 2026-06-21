package ifba.inf011.state.baseclass;

// State
public interface ModeState {
    ModeState lock(AdvancedPlayer context);
    ModeState play(AdvancedPlayer context);
    ModeState next(AdvancedPlayer context);
    ModeState previous(AdvancedPlayer context);
    String name();
}