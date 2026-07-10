package ifba.inf011.p3_2026_1.avaliacao1.timeline.builder;

import ifba.inf011.p3_2026_1.model.canva.Canva;
import ifba.inf011.p3_2026_1.model.encoder.Encoder;
import ifba.inf011.p3_2026_1.model.renderer.Renderer;

public interface TimelineBuilder {
	public TimelineBuilder reset();
	public TimelineBuilder setCanva(Canva canva);
	public TimelineBuilder setEncoder(Encoder encoder);
	public TimelineBuilder setRenderer(Renderer renderer);
	public TimelineBuilder addObjectAdapterVideo(String name);
	public TimelineBuilder addClassAdapterVideo(String name);
	public TimelineBuilder addAudio(String name);
	public TimelineBuilder addSubtitle(String name, String idioma);
	public Timeline build();
}