package ifba.inf011.p2.s25_1.bridge;

import ifba.inf011.p2.s25_1.model.Mapa;
import ifba.inf011.p2.s25_1.model.PontoGeografico;

//Concrete Implementor do Bridge
//Concrete Component do Decorator
public class FormatoJSON implements FormatoExportacao {

	@Override
	public String iniciarDocumento() {
		return "Início do documento JSON";
	}
	
	@Override
	public String adicionarAreaMapa(Mapa mapa) {
		return "Mapa";
	}
	
	@Override
    public String adicionarMetadados(String categoria) {
    	return "Categoria";
    }
    
	@Override
    public String adicionarPonto(String tipo, PontoGeografico ponto) {
    	return "Ponto";
    }
    
	@Override
    public String adicionarTexto(PontoGeografico ponto, String texto) {
    	return "Texto";
    }
    
	@Override
    public String finalizarDocumento() {
    	return "Final do documento JSON";
    }
}
