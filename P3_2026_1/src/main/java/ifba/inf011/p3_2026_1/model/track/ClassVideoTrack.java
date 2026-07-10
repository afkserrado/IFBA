package ifba.inf011.p3_2026_1.model.track;

import ifba.inf011.p3_2026_1.avaliacao2.adapter.ClassAdapterTrack;

public class ClassVideoTrack extends ClassAdapterTrack implements Track{
    private String streamName;

    public ClassVideoTrack(String streamName) {
        super(streamName);
    }

    public ClassVideoTrack(ClassVideoTrack vt) {
    	super(vt.streamName);
    }
    
    public ClassVideoTrack fork() {
    	return new ClassVideoTrack(this);
    }    

}
