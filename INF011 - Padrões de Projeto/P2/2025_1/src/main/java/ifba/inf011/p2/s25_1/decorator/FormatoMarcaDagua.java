package ifba.inf011.p2.s25_1.decorator;

import ifba.inf011.p2.s25_1.bridge.FormatoExportacao;
import ifba.inf011.p2.s25_1.model.Mapa;

// Concrete Decorator
public class FormatoMarcaDagua extends FormatoDecorator {

	public FormatoMarcaDagua(FormatoExportacao inner) {
		super(inner);
	}
	
	@Override
	public String adicionarAreaMapa(Mapa mapa) {
		String marcaDagua = "Marca d'água";
		String mapaOriginal = inner.adicionarAreaMapa(mapa);
		return mapaOriginal + " - " + marcaDagua;
	}
}
