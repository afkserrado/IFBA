package ifba.inf011.state.baseclass;

// Abstract State
public abstract class AbstractModeState implements ModeState {

    @Override
    public ModeState lock(AdvancedPlayer context) {
        System.out.println("Lock ignorado no estado " + name());
        return this;
    }

    @Override
    public ModeState play(AdvancedPlayer context) {
        System.out.println("Play ignorado no estado " + name());
        return this;
    }

    @Override
    public ModeState next(AdvancedPlayer context) {
        System.out.println("Next ignorado no estado " + name());
        return this;
    }

    @Override
    public ModeState previous(AdvancedPlayer context) {
        System.out.println("Previous ignorado no estado " + name());
        return this;
    }
}