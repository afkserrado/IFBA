package ifba.inf011.state.contextparameter;

// Concrete State
public class PlayingControlState implements ControlState {

    @Override
    public String pressLock(PlayerController context) {
        context.pausePlayback();
        context.setState(new LockedControlState());
        return "Player bloqueado durante a reprodução";
    }

    @Override
    public String pressPlay(PlayerController context) {
        String result = context.pausePlayback();
        context.setState(new ReadyControlState());
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
        return "PLAYING";
    }
}