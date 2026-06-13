package ifba.inf011.p2.s22_2.abstraction;

import java.util.List;

import ifba.inf011.p2.s22_2.Obra;
import ifba.inf011.p2.s22_2.implementation.Formato;

// Abstraction do Bridge
public interface Relatorio {
    public String gerarRelatorio(List<Obra> obras);
    public void setFormato(Formato formato);
}
