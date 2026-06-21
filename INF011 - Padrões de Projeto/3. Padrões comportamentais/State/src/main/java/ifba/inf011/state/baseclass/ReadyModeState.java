package ifba.inf011.state.baseclass;

// Concrete State
public class ReadyModeState extends AbstractModeState {

    @Override
    public ModeState lock(AdvancedPlayer context) {
        System.out.println("Player bloqueado");
        return new LockedModeState();
    }

    @Override
    public ModeState play(AdvancedPlayer context) {
        context.startPlayback();
        return new PlayingModeState();
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
        return "READY";
    }
}