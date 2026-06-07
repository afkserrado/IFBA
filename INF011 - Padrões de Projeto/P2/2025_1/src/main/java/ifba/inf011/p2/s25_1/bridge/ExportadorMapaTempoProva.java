package ifba.inf011.p2.s25_1.bridge;

import ifba.inf011.p2.s25_1.model.Mapa;
import ifba.inf011.p2.s25_1.model.PontoGeografico;
import ifba.inf011.p2.s25_1.model.Prisma;

// Refined Abstraction
public class ExportadorMapaTempoProva extends ExportadorMapa {

	public ExportadorMapaTempoProva(FormatoExportacao formato) {
		super(formato);
	}
	
	public String iniciarDocumento() {
    	return formato.iniciarDocumento();
    }
	
	public String adicionarMetadados(String metadado) {
    	return formato.adicionarMetadados(metadado);
    }
	
	public String adicionarPartida(PontoGeografico partida) {
    	return formato.adicionarPonto("partida", partida);
    }
    
    public String adicionarChegada(PontoGeografico chegada) {
    	return formato.adicionarPonto("chegada", chegada);
    }
    
    public String adicionarPrisma(Prisma prisma) {
    	return formato.adicionarPonto("prisma", prisma.localizacao()) + formato.adicionarTexto(prisma.localizacao(), prisma.numero().toString());
    }
    
    @Override
    public String exportarMapa(Mapa mapa) {
        StringBuilder sb = new StringBuilder();
        sb.append(formato.iniciarDocumento()).append(System.lineSeparator());
        sb.append(formato.adicionarAreaMapa(mapa)).append(System.lineSeparator());
        sb.append(adicionarPartida(mapa.partida())).append(System.lineSeparator());
        sb.append(adicionarChegada(mapa.chegada())).append(System.lineSeparator());
        sb.append(formato.finalizarDocumento());
        return sb.toString();
    }
}
