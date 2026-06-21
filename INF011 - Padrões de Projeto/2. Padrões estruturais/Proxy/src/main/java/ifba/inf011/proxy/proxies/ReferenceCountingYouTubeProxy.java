package ifba.inf011.proxy.proxies;

import java.util.HashMap;

import ifba.inf011.proxy.domain.ThirdPartyYouTubeClass;
import ifba.inf011.proxy.domain.ThirdPartyYouTubeLib;
import ifba.inf011.proxy.domain.Video;

// Proxy
public class ReferenceCountingYouTubeProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService;
    private int referenceCount = 0;

    public synchronized void acquire() {
        referenceCount++;
        if (youtubeService == null) {
            youtubeService = new ThirdPartyYouTubeClass();
            System.out.println("Smart proxy created the real service.");
        }
        System.out.println("Smart proxy references: " + referenceCount);
    }

    public synchronized void release() {
        if (referenceCount > 0) {
            referenceCount--;
        }
        System.out.println("Smart proxy references: " + referenceCount);

        if (referenceCount == 0) {
            youtubeService = null;
            System.out.println("Smart proxy released the real service.");
        }
    }

    private ThirdPartyYouTubeLib service() {
        if (youtubeService == null) {
            youtubeService = new ThirdPartyYouTubeClass();
            System.out.println("Smart proxy recreated the real service on demand.");
        }
        return youtubeService;
    }

    @Override
    public synchronized HashMap<String, Video> popularVideos() {
        return service().popularVideos();
    }

    @Override
    public synchronized Video getVideo(String videoId) {
        return service().getVideo(videoId);
    }
}