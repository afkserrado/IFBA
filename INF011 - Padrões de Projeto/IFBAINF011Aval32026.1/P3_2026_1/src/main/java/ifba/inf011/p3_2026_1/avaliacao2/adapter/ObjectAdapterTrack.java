package ifba.inf011.p3_2026_1.avaliacao2.adapter;

import ifba.inf011.p3_2026_1.model.track.Track;
import ifba.inf011.p3_2026_1.thirdPart.HDDBinaryReader;

public class ObjectAdapterTrack implements Track {

	protected String streamName;
    protected HDDBinaryReader binaryReader;
    protected boolean isFullyLoaded = false;
    protected byte[] data;

    public ObjectAdapterTrack(String streamName) {
        this.streamName = streamName;
        this.binaryReader = new HDDBinaryReader(streamName);
    }
    
    public void setStreamName(String streamName) {
    	this.streamName = streamName;
    	this.binaryReader = new HDDBinaryReader(streamName);
    }
    

    @Override
    public String getStreamName() {
        return this.streamName;
    }

    @Override
    public Integer getDurationInSeconds() {
        return this.binaryReader.getDuration();
    }

    @Override
    public void render(Integer init, Integer duration) {
	    if (!isFullyLoaded) {
	        this.data = this.binaryReader.getRawData();
	        this.isFullyLoaded = true;
	    }
    }
}