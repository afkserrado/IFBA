package ifba.inf011.p3_2026_1.avaliacao2.decorator;

public class TrailerDecorator extends AbstractTimelineDecorator{
	
    private String nomeTrailer;
    private final Integer duracaoTrailer = 120; 

    public TrailerDecorator(RenderableContent content, String nomeTrailer) {
        super(content);
        this.nomeTrailer = nomeTrailer;
    }

    @Override
    public Integer getDurationInSeconds() {
        return super.getDurationInSeconds() + this.duracaoTrailer;
    }

    @Override
    public void render(Integer init, Integer duration) {
    	
    	Integer fimDesejado = init + duration;
    	Integer initRelativo = Math.max(0, init - this.duracaoTrailer);
    	Integer duracaoRelativa = fimDesejado - Math.max(this.duracaoTrailer, init);
        
        this.play(init, fimDesejado);
        if (fimDesejado > this.duracaoTrailer)
            super.render(initRelativo, duracaoRelativa);
    }

	private void play(Integer init, Integer fimDesejado) {
		if (init < this.duracaoTrailer)
            System.out.println("[TrailerDecorator] Trailer '" + this.nomeTrailer 
                               + "' do segundo " + init + "s até " + Math.min(fimDesejado, this.duracaoTrailer) + "s.");
	}
}