package ifba.inf011.p2.s22_2.abstraction;

import ifba.inf011.p2.s22_2.implementation.Formato;

// Abstract Abstraction do Bridge
public abstract class AbstractRelatorio implements Relatorio {
    
    protected Formato formato;

    public AbstractRelatorio(Formato formato) {
        this.formato = formato;
    }

    public Formato getFormato() {
        return this.formato;
    }

    @Override
    public void setFormato(Formato formato) {
        this.formato = formato;
    }
}
