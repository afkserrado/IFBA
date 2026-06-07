package ifba.inf011.p2.s25_1.bridge;

import ifba.inf011.p2.s25_1.model.Mapa;
import ifba.inf011.p2.s25_1.model.PontoGeografico;

// Implementor do Bridge
// Component do Decorator
public interface FormatoExportacao {
	public abstract String iniciarDocumento();
	public abstract String adicionarAreaMapa(Mapa mapa);
    public abstract String adicionarMetadados(String categoria);
    public abstract String adicionarPonto(String tipo, PontoGeografico ponto);
    public abstract String adicionarTexto(PontoGeografico ponto, String texto);
    public abstract String finalizarDocumento();
}
