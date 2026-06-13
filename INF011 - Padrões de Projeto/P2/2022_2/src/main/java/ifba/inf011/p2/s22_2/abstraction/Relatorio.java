package ifba.inf011.p2.s22_2.abstraction;

import java.util.List;

import ifba.inf011.p2.s22_2.Obra;

// Abstraction do Bridge
public interface Relatorio {
    public String gerarRelatorio(List<Obra> obras);
}
