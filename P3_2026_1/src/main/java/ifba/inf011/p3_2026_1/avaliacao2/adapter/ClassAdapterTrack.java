package ifba.inf011.p3_2026_1.avaliacao2.adapter;

import ifba.inf011.p3_2026_1.model.track.Track;
import ifba.inf011.p3_2026_1.thirdPart.HDDBinaryReader;

public class ClassAdapterTrack extends HDDBinaryReader implements Track {

    protected String streamName;

    public ClassAdapterTrack(String streamName) {
        super(streamName);
        this.streamName = streamName;
    }
    
    public void setStreamName(String streamName) {
    	this.streamName = streamName;
    	this.path = streamName;
    	this.loadHeaderMetadata(streamName);
        this.rawBuffer = null; 
    }
    
    @Override
    public String getStreamName() {
        return this.streamName;
    }

    @Override
    public Integer getDurationInSeconds() {
        return super.getDuration();
    }

    @Override
    public void render(Integer init, Integer duration) {
        if (this.rawBuffer == null)
            this.loadPhysicalSectors();
    }
}
