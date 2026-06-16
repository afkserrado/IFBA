package ifba.inf011.p2.s26_1.q1.bridge;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;

// Refined Abstraction
public class MOVExportador extends AbstractExportador {

    public MOVExportador(NivelConteudo nivel) {
        super(nivel);
    }

    @Override
    public String exportar(Timeline dados) {
        
        StringBuilder sb = new StringBuilder();

        sb.append("Dados em formato MOV - Início");
        sb.append("\n");
        
        sb.append(nivel.gerar(dados));

        sb.append("Dados em formato MOV - Fim");
        
        return sb.toString();
    }
}
