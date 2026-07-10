package ifba.inf011.p3_2026_1.model.track;

public interface Track{
	public String getStreamName();
	public Integer getDurationInSeconds();
	public void render(Integer init, Integer duration);
}