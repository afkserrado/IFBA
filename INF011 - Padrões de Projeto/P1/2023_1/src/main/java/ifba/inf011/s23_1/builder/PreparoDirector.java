package ifba.inf011.s23_1.builder;

import ifba.inf011.s23_1.classes.Alimento;
import ifba.inf011.s23_1.classes.Nutriente;
import ifba.inf011.s23_1.singleton.CatalogoNutrientes;

public class PreparoDirector {
    
    private PreparoBuilder builder;

    public PreparoDirector(PreparoBuilder builder) {
        this.builder = builder;
        CatalogoNutrientes.getInstance().create("CARBOIDRATO", "GRAMA", 4.0);
        CatalogoNutrientes.getInstance().create("PROTEINA", "GRAMA", 4.0);
        CatalogoNutrientes.getInstance().create("GORDURA", "GRAMA", 9.0);
    }

    public void setBuilder(PreparoBuilder builder) {
        this.builder = builder;
    }

    public void fazerOvoFrito() {
        
        Alimento ovo = new Alimento("OVO");
        Alimento manteiga = new Alimento("MANTEIGA");
        Alimento sal = new Alimento("SAL");

        Nutriente sodio = CatalogoNutrientes.getInstance().create("SODIO", "MICROGRAMA", 0.0);

        sal.addNutriente(sodio, 400.0);

        this.builder.reset();
        this.builder.addAlimento(ovo);
		this.builder.addAlimento(manteiga);
		this.builder.addAlimento(sal);
		this.builder.setLowCarb();
		this.builder.setNome("OVO FRITO");
    }
}
