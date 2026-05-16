package ifba.inf011.q1.classes;

import java.time.LocalDate;

import ifba.inf011.q1.interfaces.IOperador;

public class Assinatura {
    
    private final IOperador operador;
    private final LocalDate data;

    public Assinatura(IOperador operador, LocalDate data) {
        this.operador = operador;
        this.data = data;
    }

    public IOperador getOperador() {
        return operador;
    }

    public LocalDate getData() {
        return data;
    }
}
