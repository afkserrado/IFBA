package ifba.inf011.state.contextparameter;

// Concrete State
public class ReadyControlState implements ControlState {

    @Override
    public String pressLock(PlayerController context) {
        context.setState(new LockedControlState());
        return "Player bloqueado";
    }

    @Override
    public String pressPlay(PlayerController context) {
        String result = context.startPlayback();
        context.setState(new PlayingControlState());
        return result;
    }

    @Override
    public String pressNext(PlayerController context) {
        return context.nextTrack();
    }

    @Override
    public String pressPrevious(PlayerController context) {
        return context.previousTrack();
    }

    @Override
    public String name() {
        return "READY";
    }
}