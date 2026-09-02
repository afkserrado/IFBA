package ifba.inf011.p3_2026_1.avaliacao1.timeline.builder;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.avaliacao1.prototype.Forkable;
import ifba.inf011.p3_2026_1.avaliacao2.decorator.RenderableContent;
import ifba.inf011.p3_2026_1.model.canva.Canva;
import ifba.inf011.p3_2026_1.model.encoder.Encoder;
import ifba.inf011.p3_2026_1.model.renderer.Renderer;
import ifba.inf011.p3_2026_1.model.track.AudioTrack;
import ifba.inf011.p3_2026_1.model.track.SubTitleTrack;
import ifba.inf011.p3_2026_1.model.track.Track;

public class Timeline implements Forkable, RenderableContent{
	
    private Canva canvas; 
    private Renderer renderer; 
    private Encoder encoder;
    private List<Track> videoTracks = new ArrayList<>();
    private List<AudioTrack> audioTracks = new ArrayList<>();
    private List<SubTitleTrack> subtitleTracks = new ArrayList<>();

    protected Timeline() {}

    protected void setCanvas(Canva canvas) {
    	this.canvas = canvas; 
    }
    protected void setRenderer(Renderer renderer) {
    	this.renderer = renderer; 
    }
    protected void setEncoder(Encoder encoder) {
    	this.encoder = encoder; 
    }
    public void addVideoTrack(Track track) {
    	this.videoTracks.add(track); 
    }
    public void addAudioTrack(AudioTrack track) {
    	this.audioTracks.add(track); 
    }
    public void addSubTitleTrack(SubTitleTrack track) {
    	this.subtitleTracks.add(track); 
    }
    
    public List<SubTitleTrack> getSubtitleTracks() {
    	return subtitleTracks; 
    }
    
    @Override
    public Timeline fork() {
    	Timeline fork = new Timeline();
    	fork.canvas = this.canvas;
        fork.renderer = fork.renderer;
        fork.encoder = fork.encoder;
        fork.videoTracks = new ArrayList<>(this.videoTracks);
        fork.audioTracks = new ArrayList<>(this.audioTracks);
        fork.subtitleTracks = new ArrayList<>();
        for (SubTitleTrack track : this.subtitleTracks)
            fork.subtitleTracks.add(track.fork());
        return fork;
    }  
    
    @Override
    public void render(Integer inicio, Integer duration) {
    	Integer fimDesejado = inicio + duration;
    	Integer fimReal  = 0;
    	fimReal = Math.max(fimReal, this.renderChannel(this.videoTracks, inicio, fimDesejado));
    	fimReal = Math.max(fimReal, this.renderChannel(this.audioTracks, inicio, fimDesejado));
    	fimReal = Math.max(fimReal,  this.renderChannel(this.subtitleTracks, inicio, fimDesejado));
        System.out.println("[Timeline] >> Timeline Principal  do segundo " + 
        					inicio + "s até " + fimReal + "s.");    	
    }

	private Integer renderChannel(List<? extends Track> tracks, Integer inicio, Integer fimDesejado) {
		Integer tempoAcumuladoVideo = 0;
        for (Track track : tracks) {
        	Integer trackDuracao = track.getDurationInSeconds();
        	Integer trackInicioNaTimeline = tempoAcumuladoVideo;
        	Integer trackFimNaTimeline = trackInicioNaTimeline + trackDuracao;
            if (inicio < trackFimNaTimeline && fimDesejado > trackInicioNaTimeline) {
            	Integer initRelativo = Math.max(0, inicio - trackInicioNaTimeline);
            	Integer duracaoRelativa = Math.min(trackFimNaTimeline, fimDesejado) - Math.max(trackInicioNaTimeline, inicio);
                track.render(initRelativo, duracaoRelativa);
            }
            tempoAcumuladoVideo += trackDuracao;
        }
        return tempoAcumuladoVideo;
	}
    
    @Override
    public Integer getDurationInSeconds() {
        Integer maxDuration = 0;
        Integer trackDuration = 0;
        trackDuration = this.getChannelDuration(this.videoTracks);
        maxDuration = trackDuration > maxDuration ? trackDuration : maxDuration;
        trackDuration = this.getChannelDuration(this.audioTracks);
        maxDuration = trackDuration > maxDuration ? trackDuration : maxDuration;
        trackDuration = this.getChannelDuration(this.subtitleTracks);
        maxDuration = trackDuration > maxDuration ? trackDuration : maxDuration;
        return maxDuration;
    }

	private Integer getChannelDuration(List<? extends Track> tracks) {
		Integer trackDuration;
		trackDuration = 0;
        for (Track track : tracks)
        	trackDuration += track.getDurationInSeconds();
		return trackDuration;
	}    
}