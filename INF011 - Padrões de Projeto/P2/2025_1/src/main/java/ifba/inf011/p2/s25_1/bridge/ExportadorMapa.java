package ifba.inf011.p2.s25_1.bridge;

import ifba.inf011.p2.s25_1.model.Mapa;

// Abstraction
public abstract class ExportadorMapa {
	
	protected FormatoExportacao formato;
	
	public ExportadorMapa(FormatoExportacao formato) {
		this.formato = formato;
	}
	
	public abstract String exportarMapa(Mapa mapa);
}
