package ifba.inf011.p3_2026_1.model.track;

import ifba.inf011.p3_2026_1.avaliacao2.adapter.ObjectAdapterTrack;

public class ObjectVideoTrack extends ObjectAdapterTrack implements Track{
    private String streamName;

    public ObjectVideoTrack(String streamName) {
        super(streamName);
    }

    public ObjectVideoTrack(ObjectVideoTrack vt) {
    	super(vt.streamName);
    }
    
    public ObjectVideoTrack fork() {
    	return new ObjectVideoTrack(this);
    }    

}
