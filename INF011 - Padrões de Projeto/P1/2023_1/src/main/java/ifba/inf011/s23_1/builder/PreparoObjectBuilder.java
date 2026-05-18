package ifba.inf011.s23_1.builder;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.s23_1.classes.Alimento;
import ifba.inf011.s23_1.classes.Preparo;

public class PreparoObjectBuilder implements PreparoBuilder {
    
    private String nome;
	private List<Alimento> alimentos;
	private boolean lowCarb;
	private boolean vegano;
	private boolean semLactose;
	private boolean semGluten;
	private boolean reduzidoSodio;
	private String modoDeFazer;

    @Override public void reset() {
        this.nome = null;
		this.alimentos = new ArrayList<>();
		this.lowCarb = false;
		this.vegano = false;
		this.semLactose = false;
		this.semGluten = false;
		this.reduzidoSodio = false;
		this.modoDeFazer = "";
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setLowCarb() {
        this.lowCarb = true;
    }
    
    @Override
    public void setVegano() {
        this.vegano = true;
    }
    
    @Override
    public void addAlimento(Alimento alimento) {
        this.alimentos.add(alimento);
    }

    // Método build do builder
    // Não precisa estar na interface
    public Preparo build() {
        return new Preparo(this.nome, this.alimentos, this.lowCarb, 
						   this.vegano, this.semLactose, this.semGluten,
						   this.reduzidoSodio, this.modoDeFazer);
    }    
}
