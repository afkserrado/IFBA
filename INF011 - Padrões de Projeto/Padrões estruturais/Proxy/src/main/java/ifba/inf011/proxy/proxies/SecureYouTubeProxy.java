package ifba.inf011.proxy.proxies;

import java.util.HashMap;

import ifba.inf011.proxy.domain.ThirdPartyYouTubeClass;
import ifba.inf011.proxy.domain.ThirdPartyYouTubeLib;
import ifba.inf011.proxy.domain.Video;

// Proxy
public class SecureYouTubeProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib youtubeService;
    private final String userRole;

    public SecureYouTubeProxy(String userRole) {
        this.userRole = userRole;
    }

    private ThirdPartyYouTubeLib service() {
        if (youtubeService == null) {
            youtubeService = new ThirdPartyYouTubeClass();
        }
        return youtubeService;
    }

    private boolean canAccess(String videoId) {
        System.out.println("Secure proxy checking access for role '" + userRole + "' on " + videoId);
        return "ADMIN".equalsIgnoreCase(userRole) || !videoId.startsWith("secret");
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        return service().popularVideos();
    }

    @Override
    public Video getVideo(String videoId) {
        if (!canAccess(videoId)) {
            System.out.println("ACCESS DENIED for video: " + videoId);
            return new Video(videoId, "ACCESS DENIED");
        }
        return service().getVideo(videoId);
    }
}