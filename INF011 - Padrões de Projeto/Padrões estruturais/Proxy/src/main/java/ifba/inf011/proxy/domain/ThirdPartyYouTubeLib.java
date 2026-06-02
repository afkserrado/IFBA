package ifba.inf011.proxy.domain;

import java.util.HashMap;

// Subject/Service interface
public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();
    Video getVideo(String videoId);
}