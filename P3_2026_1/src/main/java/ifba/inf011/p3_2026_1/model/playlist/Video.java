package ifba.inf011.p3_2026_1.model.playlist;

import ifba.inf011.p3_2026_1.avaliacao3.visitor.PlaylistItem;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

// Concrete Element do Visitor
public class Video implements PlaylistItem {
	
    public String nome;
    public double tamanhoMegaBytes;
    public String link;

    public Video(String nome, double tamanho, String link) { 
        this.nome = nome; 
        this.tamanhoMegaBytes = tamanho; 
        this.link = link;
    }
    
    public String getNome() {
    	return this.nome;
    }
    
    public double getTamanhoMegaBytes() {
    	return this.tamanhoMegaBytes;
    }

    public String getLink() {
		return this.link;
	}

	// Para implementação do Visitor
	@Override
	public void accept(VisitorPlaylist visitor) {
		visitor.visit(this);
	}
	
}
