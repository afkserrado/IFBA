package ifba.inf011.p3_2026_1.model.playlist;

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

	@Override
	public String toXML() {
		return "<mp3 nome=\"" + this.getNome() + "\"/>\n";
	}

	@Override
	public Double getBandwidth(Double bandPerSecond) {
		return this.getTamanhoMegaBytes();
	}
    
}
