package ifba.inf011.flyweight;

import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
public final class TreeFactory {

    private static final Map<String, TreeType> pool = new HashMap<>();

    private TreeFactory() {}

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "|" + color + "|" + texture;

        if (!pool.containsKey(key)) {
            pool.put(key, new TreeType(name, color, texture));
        }

        return pool.get(key);
    }

    public static int poolSize() {
        return pool.size();
    }
}