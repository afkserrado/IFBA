package ifba.inf011.p3_2026_1.avaliacao2.decorator;

public interface RenderableContent {
	public Integer getDurationInSeconds();
	public void render(Integer init, Integer duration);
}