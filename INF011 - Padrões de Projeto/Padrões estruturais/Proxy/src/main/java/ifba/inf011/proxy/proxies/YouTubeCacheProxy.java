package ifba.inf011.proxy.proxies;

import java.util.HashMap;

import ifba.inf011.proxy.domain.ThirdPartyYouTubeClass;
import ifba.inf011.proxy.domain.ThirdPartyYouTubeLib;
import ifba.inf011.proxy.domain.Video;

// Proxy
public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService;
    private HashMap<String, Video> cachePopular;
    private final HashMap<String, Video> cacheAll = new HashMap<>();

    private ThirdPartyYouTubeLib service() {
        if (youtubeService == null) {
            youtubeService = new ThirdPartyYouTubeClass();
        }
        return youtubeService;
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular == null) {
            cachePopular = service().popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = service().getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset() {
        cachePopular = null;
        cacheAll.clear();
        youtubeService = null;
    }
}