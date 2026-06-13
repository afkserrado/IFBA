package ifba.inf011.p2.s22_2.implementation;

// Abstract Implementation do Bridge
public abstract class AbstractFormato implements Formato {
    
    protected StringBuilder relatorio;
    private boolean iniciado = false;

    public boolean getIniciado() {
        return this.iniciado;
    }

    public void setIniciado(boolean estado) {
        this.iniciado = estado;
    }

    public void foiIniciado() {
        if(!this.iniciado) {
            throw new IllegalStateException(
                "inicioDocumento() precisa ser executado antes."
            );
        }
    }
}
