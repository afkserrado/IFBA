package ifba.inf011.state.contextparameter;

// State
public interface ControlState {
    String pressLock(PlayerController context);
    String pressPlay(PlayerController context);
    String pressNext(PlayerController context);
    String pressPrevious(PlayerController context);
    String name();
}