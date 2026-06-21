package ifba.inf011.state.baseclass;

// Concrete State
public class PlayingModeState extends AbstractModeState {

    @Override
    public ModeState lock(AdvancedPlayer context) {
        context.pausePlayback();
        System.out.println("Player bloqueado");
        return new LockedModeState();
    }

    @Override
    public ModeState play(AdvancedPlayer context) {
        context.pausePlayback();
        return new ReadyModeState();
    }

    @Override
    public ModeState next(AdvancedPlayer context) {
        context.nextTrack();
        return this;
    }

    @Override
    public ModeState previous(AdvancedPlayer context) {
        context.previousTrack();
        return this;
    }

    @Override
    public String name() {
        return "PLAYING";
    }
}