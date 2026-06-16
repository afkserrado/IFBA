package ifba.inf011.p2.s26_1.q1.decorator;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.q1.bridge.NivelConteudo;

// Concrete Decorator
public class MarcaDaguaDecorator extends NivelBaseDecorator {
    
    public MarcaDaguaDecorator(NivelConteudo inner) {
        super(inner);
    }

    @Override
    public String gerar(Timeline dados) {
        StringBuilder sb = new StringBuilder();
        sb.append("Início da marca d'água...");
        sb.append("\n");
        sb.append(super.gerar(dados));
        sb.append("Fim da marca d'água...");
        sb.append("\n");

        return sb.toString();
    }
}
