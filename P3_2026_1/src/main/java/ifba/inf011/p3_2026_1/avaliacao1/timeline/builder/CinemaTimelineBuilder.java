package ifba.inf011.p3_2026_1.avaliacao1.timeline.builder;

import ifba.inf011.p3_2026_1.model.canva.Canva;
import ifba.inf011.p3_2026_1.model.canva.Canva4K;
import ifba.inf011.p3_2026_1.model.encoder.Encoder;
import ifba.inf011.p3_2026_1.model.encoder.ProResEncoder;
import ifba.inf011.p3_2026_1.model.renderer.HighPrecisionRenderer;
import ifba.inf011.p3_2026_1.model.renderer.Renderer;
import ifba.inf011.p3_2026_1.model.track.AudioTrack;
import ifba.inf011.p3_2026_1.model.track.ClassVideoTrack;
import ifba.inf011.p3_2026_1.model.track.ObjectVideoTrack;
import ifba.inf011.p3_2026_1.model.track.SubTitleTrack;

public class CinemaTimelineBuilder implements TimelineBuilder {
	
    private Timeline timeline;
    private Canva canva;
    private Renderer renderer;
    private Encoder encoder;

    public CinemaTimelineBuilder() {
    	this.reset(); 
    }

    @Override
    public TimelineBuilder reset() {
        this.timeline = new Timeline();
        this.canva = new Canva4K();
        this.renderer = new HighPrecisionRenderer();
        this.encoder = new ProResEncoder();
        return this;
    }

	@Override
	public TimelineBuilder setCanva(Canva canva) {
		this.canva = canva;
		return this;
	}

	@Override
	public TimelineBuilder setEncoder(Encoder encoder) {
		this.encoder = encoder;
		return this;
	}

	@Override
	public TimelineBuilder setRenderer(Renderer renderer) {
		this.renderer = renderer;
		return null;
	}
	
    public TimelineBuilder addClassAdapterVideo(String name) {
    	this.timeline.addVideoTrack(new ClassVideoTrack(name)); 
    	return this; 
    }
    
    public TimelineBuilder addObjectAdapterVideo(String name) {
    	this.timeline.addVideoTrack(new ObjectVideoTrack(name)); 
    	return this; 
    }
        
    
    public TimelineBuilder addAudio(String name) {
    	this.timeline.addAudioTrack(new AudioTrack(name)); 
    	return this;
    }
    
    public TimelineBuilder addSubtitle(String name, String idioma) {
    	this.timeline.addSubTitleTrack(new SubTitleTrack(name, idioma));
    	return this; 
    }

    public Timeline build() {
        this.renderer.initialize(this.canva);
        this.encoder.initialize(this.renderer);
        this.timeline.setCanvas(this.canva);
        this.timeline.setRenderer(this.renderer);
        this.timeline.setEncoder(this.encoder);
        return this.timeline;
    }


}