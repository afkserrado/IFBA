package ifba.inf011.s23_1.builder;

import ifba.inf011.s23_1.classes.Alimento;
import ifba.inf011.s23_1.classes.ValorNutricional;

// Builder concreto
public class ValorNutricionalBuilder implements PreparoBuilder {
    
    private Double cho;
	private Double ptn;
	private Double lip;	
	
	@Override
	public void reset() {
		this.cho = 0.0;
		this.ptn = 0.0;
		this.lip = 0.0;
	}

	@Override
	public void addAlimento(Alimento alimento) {
		
	}

    @Override
	public void setNome(String nome) {

    }

    @Override
    public void setLowCarb() {

    }

    @Override
    public void setVegano() {

    }
	
	public ValorNutricional build() {
		return new ValorNutricional(this.cho, this.ptn, this.lip);
	}
}
