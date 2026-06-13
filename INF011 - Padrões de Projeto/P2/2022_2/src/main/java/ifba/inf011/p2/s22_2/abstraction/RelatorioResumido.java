package ifba.inf011.p2.s22_2.abstraction;

import ifba.inf011.p2.s22_2.Obra;
import ifba.inf011.p2.s22_2.implementation.Formato;

// Refined Abstraction
public class RelatorioResumido extends AbstractRelatorio {
    
    public RelatorioResumido(Formato formato) {
        super(formato);
    }

    @Override
    public String gerarRelatorio(Obra obra) {
        return "";
    }
}
