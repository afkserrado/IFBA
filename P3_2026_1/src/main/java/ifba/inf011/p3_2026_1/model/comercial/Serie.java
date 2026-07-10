package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;

public class Serie {

	protected String titulo;
	protected Integer temporada;
    protected List<Episodio> episodios;
    
    public Serie(String titulo, Integer temporada) {
    	this.titulo = titulo;
    	this.episodios = new ArrayList<Episodio>();
    };
    
    public String getTitulo() {
    	return this.titulo;
    }
        
    public Double getPreco() {
        double soma = this.episodios.stream().mapToDouble(Episodio::getPreco).sum();
        return soma * 0.9;
    }
        
    public Double getDuracao() {
        return  this.episodios.stream().mapToDouble(Episodio::getDuracao).sum();
    }    
    
    public Integer getTemporada() {
    	return this.temporada;
    }
    
	public String toXML() {
		String xml = "\t<serie titulo=\"" + this.getTitulo() + "\" temporada=\"" + this.getTemporada() + "\">\n";
		for(Episodio episodio : this.episodios)
			xml += episodio.toXML();
		return xml + "\t</serie>\n";
		
	}    

}