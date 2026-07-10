package ifba.inf011.p3_2026_1.model.comercial;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;

public class Episodio {
	
	private String titulo;
    private Double preco;
    private Timeline timeline;
    private Integer numero;

	public Episodio(String titulo, Double preco, Integer numero, Timeline timeline) {
    	this.titulo = titulo;
        this.preco = preco;
        this.timeline = timeline;
        this.numero = numero;
	}
	
    public Double getPreco() {
    	return this.preco; 
    }
    
    public Integer getDuracao() { 
    	return this.timeline.getDurationInSeconds();
    }

	public String getTitulo() {
		return this.titulo;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public String toXML() {
		String xml = "<episodio titulo=\"" + this.getTitulo() + "\" numero=\"" + this.getNumero() + "\"/>\n";
		return xml;
	}  
	 	
}