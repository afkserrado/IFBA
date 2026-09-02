package ifba.inf011.p3_2026_1.avaliacao2.decorator;

public class CreditsDecorator extends AbstractTimelineDecorator {
	
    private final Integer duracaoCreditos = 30;
    private String nomeCreditos;

    public CreditsDecorator(RenderableContent content, String nomeCreditos) {
        super(content);
        this.nomeCreditos = nomeCreditos;
    }

    @Override
    public Integer getDurationInSeconds() {
        return super.getDurationInSeconds() + this.duracaoCreditos;
    }

    @Override
    public void render(Integer inicio, Integer duracao) {
        Integer fimDesejado = inicio + duracao;
        Integer inicioCreditos = super.getDurationInSeconds();
        Integer initRelativo = Math.max(0, inicio - inicioCreditos);
        Integer fimCreditos = Math.min(fimDesejado - inicioCreditos, this.duracaoCreditos);
        Integer duracaoFilme = Math.min(duracao, inicioCreditos - inicio);
        if (inicio < inicioCreditos)
            super.render(inicio, duracaoFilme);
        if (fimDesejado > inicioCreditos)
            this.play(initRelativo, fimCreditos);
    }
    

	private void play(Integer inicioCreditos, Integer fimDesejado) {
		if (fimDesejado > inicioCreditos)
            System.out.println("[CreditsDecorator] >> Créditos " + this.nomeCreditos +
  				  " do segundo " + inicioCreditos + "s até " + Math.min(fimDesejado, this.duracaoCreditos) + "s.");
	}    
}
