package ifba.inf011.p3_2026_1.model.playlist;

public class Video implements PlaylistItem {
	
    public String nome;
    public double tamanhoMegaBytes;
    public String link;

    public Video(String nome, double tamanho, String link) { 
        this.nome = nome; 
        this.tamanhoMegaBytes = tamanho; 
        this.link = link;
    }
    
    public double getTamanhoMegaBytes() {
    	return this.tamanhoMegaBytes;
    }

    
    public String getNome() {
    	return this.nome;
    }

	@Override
	public String toXML() {
		return "  <video nome=\"" + this.getNome() + "\" link=\"" + this.getLink() + "\"/>\n";
	}

	private String getLink() {
		return this.link;
	}

	@Override
	public Double getBandwidth(Double bandPerSecond) {
		return this.getTamanhoMegaBytes();
	}
}
