package ifba.inf011.p3_2026_1.avaliacao2.decorator;

public abstract class AbstractTimelineDecorator implements RenderableContent {
	
	protected RenderableContent decoratedContent; 

	public AbstractTimelineDecorator(RenderableContent content) {
	        this.decoratedContent = content;
	}

	@Override
	public Integer getDurationInSeconds() {
		return this.decoratedContent.getDurationInSeconds();
	}

	@Override
	public void render(Integer init, Integer duration) {
		this.decoratedContent.render(init, duration);
	}

}
