package ifba.inf011.s23_1.classes;

import java.util.List;

// Product do builder
public class Preparo {
    
    private String nome;
	private List<Alimento> alimentos;
	private boolean lowCarb;
	private boolean lowVegano;
	private boolean semLactose;
	private boolean semGluten;
	private boolean reduzidoSodio;
	private String modoDeFazer;

    public Preparo(String nome, List<Alimento> alimentos, boolean lowCarb, boolean lowVegano, boolean semLactose, boolean semGluten, boolean reduzidoSodio, String modoDeFazer) {
		this.nome = nome;
		this.alimentos = alimentos;
		this.lowCarb = lowCarb;
		this.lowVegano = lowVegano;
		this.semLactose = semLactose;
		this.semGluten = semGluten;
		this.reduzidoSodio = reduzidoSodio;
		this.modoDeFazer = modoDeFazer;
	}
}
