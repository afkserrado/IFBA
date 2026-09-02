package ifba.inf011.p3_2026_1.model.playlist;

import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

// Concrete Element do Visitor
public class MP3 implements PlaylistItem {
    
    public String nome;
    public double tamanhoMegaBytes;

    public MP3(String nome, double tamanho) { 
        this.nome = nome; 
        this.tamanhoMegaBytes = tamanho; 
    }
    
    public double getTamanhoMegaBytes() {
    	return this.tamanhoMegaBytes;
    }

    public String getNome() {
    	return this.nome;
    }

	// Para implementação do Visitor
	@Override
	public void accept(VisitorPlaylist visitor) {
		visitor.visit(this);
	}
    
}
