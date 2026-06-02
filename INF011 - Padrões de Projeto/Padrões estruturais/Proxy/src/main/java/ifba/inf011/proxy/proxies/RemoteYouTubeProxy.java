package ifba.inf011.proxy.proxies;

import java.util.HashMap;

import ifba.inf011.proxy.domain.ThirdPartyYouTubeClass;
import ifba.inf011.proxy.domain.ThirdPartyYouTubeLib;
import ifba.inf011.proxy.domain.Video;

// Proxy
public class RemoteYouTubeProxy implements ThirdPartyYouTubeLib {
    private ThirdPartyYouTubeLib remoteService;

    private ThirdPartyYouTubeLib service() {
        if (remoteService == null) {
            remoteService = new ThirdPartyYouTubeClass();
        }
        return remoteService;
    }

    private void connectToRemoteHost() {
        System.out.print("Connecting to remote YouTube service... ");
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Connected!");
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        connectToRemoteHost();
        System.out.println("Encoding request for popularVideos()");
        HashMap<String, Video> result = service().popularVideos();
        System.out.println("Decoding response from remote service");
        return result;
    }

    @Override
    public Video getVideo(String videoId) {
        connectToRemoteHost();
        System.out.println("Encoding request for getVideo(" + videoId + ")");
        Video result = service().getVideo(videoId);
        System.out.println("Decoding response from remote service");
        return result;
    }
}