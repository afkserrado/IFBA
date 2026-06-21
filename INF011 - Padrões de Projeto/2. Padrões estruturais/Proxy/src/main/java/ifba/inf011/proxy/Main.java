package ifba.inf011.proxy;

import ifba.inf011.proxy.client.YouTubeDownloader;
import ifba.inf011.proxy.domain.ThirdPartyYouTubeClass;
import ifba.inf011.proxy.proxies.LoggingYouTubeProxy;
import ifba.inf011.proxy.proxies.ReferenceCountingYouTubeProxy;
import ifba.inf011.proxy.proxies.RemoteYouTubeProxy;
import ifba.inf011.proxy.proxies.SecureYouTubeProxy;
import ifba.inf011.proxy.proxies.YouTubeCacheProxy;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Virtual / Caching Proxy ===");
        YouTubeDownloader cachedDownloader = new YouTubeDownloader(new YouTubeCacheProxy());
        benchmark(cachedDownloader);

        System.out.println("=== Logging + Virtual / Caching Proxy ===");
        YouTubeDownloader loggedDownloader =
                new YouTubeDownloader(new LoggingYouTubeProxy(new YouTubeCacheProxy()));
        benchmark(loggedDownloader);

        System.out.println("=== Protection Proxy ===");
        YouTubeDownloader secureDownloader = new YouTubeDownloader(new SecureYouTubeProxy("USER"));
        secureDownloader.renderVideoPage("catzzzzzzzzz");
        secureDownloader.renderVideoPage("secret-episode-01");
        secureDownloader.renderPopularVideos();

        System.out.println("=== Remote Proxy ===");
        YouTubeDownloader remoteDownloader = new YouTubeDownloader(new RemoteYouTubeProxy());
        benchmark(remoteDownloader);

        System.out.println("=== Smart Reference Proxy ===");
        ReferenceCountingYouTubeProxy smartProxy = new ReferenceCountingYouTubeProxy();
        smartProxy.acquire();
        smartProxy.acquire();
        YouTubeDownloader smartDownloader = new YouTubeDownloader(smartProxy);
        benchmark(smartDownloader);
        smartProxy.release();
        smartProxy.release();

        System.out.println("=== Real Subject Directly ===");
        YouTubeDownloader directDownloader = new YouTubeDownloader(new ThirdPartyYouTubeClass());
        benchmark(directDownloader);
    }

    private static void benchmark(YouTubeDownloader downloader) {
        long startTime = System.currentTimeMillis();

        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Time elapsed: " + estimatedTime + "ms\n\n");
    }
}