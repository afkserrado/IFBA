package ifba.inf011.p3_2026_1.model.track;

import ifba.inf011.p3_2026_1.avaliacao2.adapter.ClassAdapterTrack;

public class AudioTrack extends ClassAdapterTrack implements Track{
    private String streamName;

    public AudioTrack(String streamName) {
        super(streamName);
    }

    public AudioTrack(AudioTrack at) {
    	super(at.streamName);
    }
    
    public AudioTrack fork() {
    	return new AudioTrack(this);
    }    


}
