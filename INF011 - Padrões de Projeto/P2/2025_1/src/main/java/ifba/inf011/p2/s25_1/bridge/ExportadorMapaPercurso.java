package ifba.inf011.p2.s25_1.bridge;

import java.util.List;

import ifba.inf011.p2.s25_1.model.Mapa;
import ifba.inf011.p2.s25_1.model.PontoGeografico;
import ifba.inf011.p2.s25_1.model.Prisma;

// Refined Abstraction
public class ExportadorMapaPercurso extends ExportadorMapa {

	public ExportadorMapaPercurso(FormatoExportacao formato) {
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
    
    public String adicionarLinha(List<Prisma> lista) {
        if (lista == null || lista.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Linha: ");
        
        sb.append(lista.get(0).localizacao());

        for (int i = 1; i < lista.size(); i++) {
            sb.append(" -> ").append(lista.get(i).localizacao());
        }

        sb.append(" -> ").append(lista.get(lista.size() - 1).localizacao());

        return sb.toString();
    }
    
    @Override
    public String exportarMapa(Mapa mapa) {
        StringBuilder sb = new StringBuilder();
        sb.append(formato.iniciarDocumento()).append(System.lineSeparator());
        sb.append(formato.adicionarAreaMapa(mapa)).append(System.lineSeparator());
        sb.append(adicionarLinha(mapa.prismas())).append(System.lineSeparator());
        sb.append(adicionarPartida(mapa.partida())).append(System.lineSeparator());
        sb.append(adicionarChegada(mapa.chegada())).append(System.lineSeparator());
        sb.append(formato.finalizarDocumento());
        return sb.toString();
    }
}
