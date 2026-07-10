package ifba.inf011.p3_2026_1.model.comercial;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;

public class Pacote implements PlaylistItem {

	protected String titulo;
	protected List<Filme> filmes;
	
	public Pacote(String titulo) {
		this.titulo = titulo;
		this.filmes = new ArrayList<Filme>();
	};
	
	public Pacote(String titulo, List<Filme> filmes) {
		this.titulo = titulo;
		this.filmes = filmes;
	};	    
	
	public String getTitulo() {
		return this.titulo;
	}
		
	public Double getPreco() {
		double soma = this.filmes.stream().mapToDouble(Filme::getPreco).sum();
		return soma * 0.9;
	}
		
	public Double getDuracao() {
		return  this.filmes.stream().mapToDouble(Filme::getDuracao).sum();
	}    
	
	public String toXML() {
		String filmesXML = this.filmes.stream()
				.map(Filme::toXML)
				.collect(Collectors.joining());
		return "<pacote titulo=\"" + this.getTitulo() + "\">\n" 
			+ filmesXML 
			+ "</pacote>\n";
	}

	public Double getBandwidth(Double bandPerSecond) {
		return this.getDuracao() * bandPerSecond;
	}    

}