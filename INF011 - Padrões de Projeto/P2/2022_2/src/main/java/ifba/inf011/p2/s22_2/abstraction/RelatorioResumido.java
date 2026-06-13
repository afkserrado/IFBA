package ifba.inf011.p2.s22_2.abstraction;

import java.util.List;

import ifba.inf011.p2.s22_2.Obra;
import ifba.inf011.p2.s22_2.implementation.Formato;

// Refined Abstraction
public class RelatorioResumido extends AbstractRelatorio {
    
    public RelatorioResumido(Formato formato) {
        super(formato);
    }

    @Override
    public String gerarRelatorio(List<Obra> obras) {
        this.formato.inicioDocumento();

        for(Obra obra : obras) {
            this.formato.comentario(obra.getTitulo());
            this.formato.texto(
                this.formato.negrito(obra.getTitulo())
            );
            this.formato.texto(
                this.formato.italico(
                    obra.getAutor() + 
                    " (" +
                    obra.getAno() +
                    ")"
                )
            );
        }

        return this.formato.fimDocumento();
    }
}
