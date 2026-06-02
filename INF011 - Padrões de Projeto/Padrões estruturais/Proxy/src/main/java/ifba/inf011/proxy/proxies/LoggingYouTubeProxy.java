package ifba.inf011.proxy.proxies;

import java.util.Date;
import java.util.HashMap;

import ifba.inf011.proxy.domain.ThirdPartyYouTubeLib;
import ifba.inf011.proxy.domain.Video;

// Proxy
public class LoggingYouTubeProxy implements ThirdPartyYouTubeLib {
    private final ThirdPartyYouTubeLib wrappedService;

    public LoggingYouTubeProxy(ThirdPartyYouTubeLib wrappedService) {
        this.wrappedService = wrappedService;
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        System.out.println("[LOG " + new Date() + "] popularVideos() called");
        HashMap<String, Video> result = wrappedService.popularVideos();
        System.out.println("[LOG " + new Date() + "] popularVideos() completed");
        return result;
    }

    @Override
    public Video getVideo(String videoId) {
        System.out.println("[LOG " + new Date() + "] getVideo(" + videoId + ") called");
        Video result = wrappedService.getVideo(videoId);
        System.out.println("[LOG " + new Date() + "] getVideo(" + videoId + ") completed");
        return result;
    }
}