package ifba.inf011.p2.s22_1.flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
// Compartilha os vídeos
public class VideoFlyweightFactory {
    
    private static final Map<String, VideoFlyweight> videos = new HashMap<>();

    public static VideoFlyweight getVideo(String descricao) {
        if(!videos.containsKey(descricao)) {
            videos.put(descricao, new VideoConcreteFlyweight(descricao));
        }

        return videos.get(descricao);
    }
}
