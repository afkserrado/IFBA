package ifba.inf011.p2.s25_1.decorator;

import ifba.inf011.p2.s25_1.bridge.FormatoExportacao;
import ifba.inf011.p2.s25_1.model.Mapa;
import ifba.inf011.p2.s25_1.model.PontoGeografico;

// Base Decorator
public abstract class FormatoDecorator implements FormatoExportacao {
	
	protected FormatoExportacao inner; // Objeto a ser decorado
	
	public FormatoDecorator(FormatoExportacao inner) {
		this.inner = inner;
	}
	
	@Override
	public String iniciarDocumento() {
		return inner.iniciarDocumento();
	}
	
	@Override
	public String adicionarAreaMapa(Mapa mapa) {
		return inner.adicionarAreaMapa(mapa);
	}
	
	@Override
    public String adicionarMetadados(String categoria) {
    	return inner.adicionarMetadados(categoria);
    }
    
	@Override
    public String adicionarPonto(String tipo, PontoGeografico ponto) {
    	return inner.adicionarPonto(tipo, ponto);
    }
    
	@Override
    public String adicionarTexto(PontoGeografico ponto, String texto) {
    	return inner.adicionarTexto(ponto, texto);
    }
    
	@Override
    public String finalizarDocumento() {
    	return inner.finalizarDocumento();
    }
}
